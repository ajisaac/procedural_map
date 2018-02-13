package com.aaron.dfofin.algorithms;

import java.io.File;
/*
import ar.com.hjg.pngj.ImageInfo;
import ar.com.hjg.pngj.ImageLineHelper;
import ar.com.hjg.pngj.ImageLineInt;
import ar.com.hjg.pngj.PngWriter;*/
import com.aaron.dfofin.S;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.PixmapIO;

public class SavePic {
	/**
	 * saves a picture of the map
	 * @param gameScreen 
	 * @param gameScreen 
	 * @return 
	 */
	public static void savepic(Pixmap pixMap, String name, long time){
		
		FileHandle fh;
		fh = new FileHandle(time + name +  ".png");
		PixmapIO.writePNG(fh, pixMap);
		pixMap.dispose();
		System.out.println(name + " pic");
			
	}/*
    public static void savePng(String destFilename) {
        ImageInfo imi = new ImageInfo(S.size, S.size, 8, false); // 8 bits per channel, no alpha
        // open image for writing to a output stream
        PngWriter pngw = new PngWriter(new File(destFilename), imi, true);
        // add some optional metadata (chunks)
        ImageLineInt iline = new ImageLineInt(imi);
        for (int col = 0; col < imi.cols; col++) { // this line will be written to all rows
            int r = 255;
            int g = 127;
            int b = 255 * col / imi.cols;
            ImageLineHelper.setPixelRGB8(iline, col, r, g, b); // orange-ish gradient
        }
        for (int row = 0; row < pngw.imgInfo.rows; row++) {
            pngw.writeRow(iline);
        }
        pngw.end();
    }*/
}
