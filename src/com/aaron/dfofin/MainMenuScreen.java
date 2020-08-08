package com.aaron.dfofin;

import java.io.File;
import java.util.ArrayList;

import com.aaron.dfofin.creation.CreateNewGame;
import com.aaron.dfofin.data.Chunk;
import com.aaron.dfofin.data.GeneralData;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.ui.TextField.TextFieldStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.ScalingViewport;


public class MainMenuScreen implements Screen {
  Dofin g;
  Skin skin;
  Stage stage;
  SpriteBatch batch;
  OrthographicCamera camera;
  BitmapFont bfont;
  Label text;
  Thread t;
  float timer = 0;
  CreateNewGame cr;
  GeneralData genDat;
  Chunk[][] ch;

  //Animation
  Animation dragonAnimation;
  Texture dragonSheet;
  Texture z0, z1, z2;
  TextureRegion[] dragonFrames;
  TextureRegion currentFrame;
  float dragonW;
  float dragonH;

  float stateTime;

  public MainMenuScreen(Dofin g) {
    create();
    this.g = g;
  }

  public MainMenuScreen() {
    create();
  }

  public void create() {
    String s = System.getProperty("user.home") + "\\dfofin\\genDat.DAT";
    File f = new File(s);
    if (f.exists() && !f.isDirectory()) {
      genDat = GeneralData.loadGenData();
    } else {
      genDat = new GeneralData();
      genDat.saveGenData();
    }

    ////////////////SET UP ANIMATION///////////
    z0 = new Texture(Gdx.files.internal("assets/z0.png"));
    z1 = new Texture(Gdx.files.internal("assets/z1.png"));
    z2 = new Texture(Gdx.files.internal("assets/z2.png"));
    dragonFrames = new TextureRegion[3];
    dragonFrames[0] = new TextureRegion(z0);
    dragonFrames[1] = new TextureRegion(z1);
    dragonFrames[2] = new TextureRegion(z2);
    dragonW = Gdx.graphics.getWidth() / 2 - (float) z0.getWidth() / 2;
    dragonH = Gdx.graphics.getHeight() / 2 - (float) z0.getHeight() / 2 + 135;
    dragonAnimation = new Animation(0.25f, dragonFrames);
    stateTime = 0f;

    //////////////////SET UP UI////////////////
    camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    batch = new SpriteBatch();
    //stage = new Stage(new ScreenViewport());
    ScalingViewport sv = new ScalingViewport(Scaling.none, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    sv.setCamera(camera);
    stage = new Stage(sv);

    Gdx.input.setInputProcessor(stage);

    ///////////////////////SKINS////////////////
    skin = new Skin();
    //set up skin
    int buttonWidth = 120, buttonHeight = 30;
    Pixmap pixmap = new Pixmap(buttonWidth, buttonHeight, Format.RGBA8888);
    pixmap.setColor(Color.GREEN);
    pixmap.fill();
    skin.add("white", new Texture(pixmap));
    //set up font
    bfont = new BitmapFont();
    bfont.setColor(Color.BLACK);
    skin.add("default", bfont);

    ////////////////////STYLES//////////////////
    //create button style to add to skin
    TextButtonStyle textButtonStyle = new TextButtonStyle();
    textButtonStyle.up = skin.newDrawable("white", Color.DARK_GRAY);
    textButtonStyle.down = skin.newDrawable("white", Color.BLUE);
    textButtonStyle.over = skin.newDrawable("white", Color.GRAY);
    textButtonStyle.font = skin.getFont("default");
    skin.add("default", textButtonStyle);//add button style to this particular skin
    //textfield style
    TextFieldStyle textFieldStyle = new TextFieldStyle();
    textFieldStyle.background = skin.newDrawable("white", Color.GRAY);
    textFieldStyle.font = skin.getFont("default");
    textFieldStyle.fontColor = skin.getFont("default").getColor();

    ///////////////////GROUPS///////////////////
    final Table groupMainMenu = new Table();
    final Table groupNewGame = new Table();

    //////////////////ACTORS////////////////////
    //mainMenuGroup
    final TextButton textButtonContinue = new TextButton("CONTINUE", textButtonStyle);
    final TextButton textButtonNewGame = new TextButton("NEW GAME", textButtonStyle);
    final TextButton textButtonSettings = new TextButton("SETTINGS", textButtonStyle);
    final TextButton textButtonUtilities = new TextButton("UTILITIES", textButtonStyle);
    final TextButton textButtonQuit = new TextButton("QUIT", textButtonStyle);
    //newGameGroup
    final TextField textFieldNewGameName = new TextField("", textFieldStyle);
    final TextButton textButtonCreateGame = new TextButton("CREATE GAME", textButtonStyle);
    //continueGroup

    final Table scrollTableContinue = new Table();
    final Table scrollTab = new Table();
    final ScrollPane scroller = new ScrollPane(scrollTab);
    scrollTableContinue.add(scroller).width(textButtonContinue.getWidth()).height(textButtonContinue.getHeight() * 14);
    //scrollTableContinue.add(scroller).fill().expand();
    scrollTableContinue.row();
    scrollTableContinue.setX(Gdx.graphics.getWidth() / 2);
    scrollTableContinue.setY(Gdx.graphics.getHeight() / 2);
    stage.addActor(scrollTableContinue);

    ArrayList<TextButton> tb = new ArrayList<TextButton>();/*
		for(int i = 0; i < genDat.getGames().size(); i++){
			tb.add(new TextButton(genDat.getGames().get(genDat.getGames().size()-(i+1)), textButtonStyle));
			scrollTab.add(tb.get(i));
			scrollTab.row();
			System.out.println(genDat.getGames().get(i));
		}		*/
    for (int i = 0; i < 100; i++) {
      tb.add(new TextButton(Integer.toString(i), textButtonStyle));
      scrollTab.add(tb.get(i));
      scrollTab.row();
      //System.out.println(genDat.getGames().get(i));
    }
    scrollTab.validate();
    scrollTableContinue.validate();
    scroller.validate();


    /////////////////SETUP ACTOR PROPERTIES///
    //center
    float w = Gdx.graphics.getWidth() / 2 - (float) buttonWidth / 2;
    float h = Gdx.graphics.getHeight() / 2 - (float) buttonHeight / 2;
    //mainMenuGroup
    groupMainMenu.setPosition(w, h);
    textButtonContinue.setPosition(0, 70);
    textButtonNewGame.setPosition(0, 35);
    textButtonSettings.setPosition(0, 0);
    textButtonUtilities.setPosition(0, -35);
    textButtonQuit.setPosition(0, -70);
    //newGameGroup
    groupNewGame.setPosition(w, h);
    textFieldNewGameName.setPosition(0, 70);
    textFieldNewGameName.setMaxLength(18);
    textButtonCreateGame.setPosition(0, 35);

    ///////////ADD ACTORS TO GROUPS////////////
    //mainMenuGroup
    groupMainMenu.addActor(textButtonContinue);
    groupMainMenu.addActor(textButtonNewGame);
    groupMainMenu.addActor(textButtonSettings);
    groupMainMenu.addActor(textButtonUtilities);
    groupMainMenu.addActor(textButtonQuit);
    stage.addActor(groupMainMenu);
    //newGameGroup
    groupNewGame.addActor(textFieldNewGameName);
    groupNewGame.addActor(textButtonCreateGame);
    stage.addActor(groupNewGame);

    ////////////VISIBILITY/////////////////////
    groupNewGame.setVisible(false);
    scrollTableContinue.setVisible(false);

    //////////////LISTENERS////////////////////
    //mainMenuGroup
    //CONTINUE
    textButtonContinue.addListener(new ChangeListener() {
      public void changed(ChangeEvent event, Actor actor) {
        System.out.println("Continue");
        groupMainMenu.setVisible(false);
        scrollTableContinue.setVisible(true);
      }
    });
    //NEW GAME
    textButtonNewGame.addListener(new ChangeListener() {
      public void changed(ChangeEvent event, Actor actor) {
        System.out.println("New Game");
        //textButtonContinue.setVisible(false);
        groupMainMenu.setVisible(false);
        groupNewGame.setVisible(true);

      }
    });
    //SETTINGS
    textButtonSettings.addListener(new ChangeListener() {
      public void changed(ChangeEvent event, Actor actor) {
        System.out.println("Settings");
        //g.setScreen(new GameScreen(g));
      }
    });
    //UTILITIES
    textButtonUtilities.addListener(new ChangeListener() {
      public void changed(ChangeEvent event, Actor actor) {
        System.out.println("Utilities");
        //g.setScreen(new GameScreen(g));
      }
    });
    //QUIT
    textButtonQuit.addListener(new ChangeListener() {
      public void changed(ChangeEvent event, Actor actor) {
        System.out.println("Quit");
        Gdx.app.exit();
      }
    });
    ///////////////LISTENERS////////////////////
    //newGameGroup
    //CREATE GAME
    textButtonCreateGame.addListener(new ChangeListener() {
      public void changed(ChangeEvent event, Actor actor) {
        S.gameName = System.getProperty("user.home") + "\\dfofin\\" + textFieldNewGameName.getText();
        genDat.getGames().add(textFieldNewGameName.getText());
        genDat.saveGenData();
        new File(S.gameName).mkdirs();
        cr = new CreateNewGame();
        t = new Thread(cr);
        t.start();
      }
    });


  }

  public void loadTheNewGame(Chunk[][] ch) {
    g.setScreen(new GameScreen(g, ch));
  }

  @Override
  public void render(float delta) {
    Gdx.gl.glClearColor(0, 0.5f, 0.9f, 1);
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    stateTime += delta;
    timer += Gdx.graphics.getDeltaTime();
    currentFrame = dragonAnimation.getKeyFrame(stateTime, true);
    stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
    stage.draw();
    camera.update();
    batch.setProjectionMatrix(stage.getCamera().combined);
    batch.begin();
    batch.draw(currentFrame, dragonW, dragonH);
    batch.end();

    if (timer >= 1) {
      if (t != null) {
        //System.out.println(t.isAlive());
        ch = cr.getArray();
        if (ch != null) {
          t = null;
          loadTheNewGame(ch);
        }
      }
      timer -= 1;
    }
  }

  @Override
  public void resize(int width, int height) {
    stage.getViewport().update(width, height);

  }

  @Override
  public void show() {

  }

  @Override
  public void pause() {

  }

  @Override
  public void resume() {

  }

  @Override
  public void hide() {
    dispose();
  }

  @Override
  public void dispose() {
    stage.dispose();
    skin.dispose();
  }


}
