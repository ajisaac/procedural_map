package com.aaron.dfofin.algorithms;

import java.util.Random;

import com.aaron.dfofin.S;

/*
 * todo Figure out how this works.
 */

public class Noise {
	Random rand;
	public Noise(){
		
		if(S.randSeed == true){
			rand = new Random();
		}else
			rand = new Random(S.seed);
	}

	
	/**
	 * @param width - X size of array
	 * @param height - Y size of array
	 * @return <b>float[][]</b> random noise<p>
	 * Creates an array of white noise
	 */
	public float[][] generateWhiteNoise(int width, int height) {
		float[][] noise = new float[width][height];
		for(int i = 0; i < width; i++){
			for(int j = 0; j < height; j++){
				noise[i][j] = rand.nextFloat();
			}
		}
		return noise;
	}

	public float interpolate (float a, float b, float x) {
		return a * (1 - x) + x * b;
	}

	public float cosInter (float a, float b, float x) {
		double ft = x * 3.1415927;
		double f = (1 - Math.cos(ft)) * .5;
		float u = (float) (a*(1-f) + b*f);
		return u;
	}


	
	public float[][] generateSmoothNoiseInterpolate (float[][] baseNoise, int octave, int size) {
		int width = size;
		int height = size;
		float[][] smoothNoise = new float[width][height];

		int samplePeriod = 1 << octave; // calculates 2 ^ k
		float sampleFrequency = 1f / samplePeriod;
		for (int i = 0; i < width; i++) {
			int sample_i0 = (i / samplePeriod) * samplePeriod;
			int sample_i1 = (sample_i0 + samplePeriod) % width; // wrap around
			float horizontal_blend = (i - sample_i0) * sampleFrequency;

			for (int j = 0; j < height; j++) {
				int sample_j0 = (j / samplePeriod) * samplePeriod;
				int sample_j1 = (sample_j0 + samplePeriod) % height; // wrap around
				float vertical_blend = (j - sample_j0) * sampleFrequency;
				float top = interpolate(baseNoise[sample_i0][sample_j0], baseNoise[sample_i1][sample_j0], horizontal_blend);
				float bottom = interpolate(baseNoise[sample_i0][sample_j1], baseNoise[sample_i1][sample_j1], horizontal_blend);
				smoothNoise[i][j] = interpolate(top, bottom, vertical_blend);
				
			}
		}

		return smoothNoise;
	}
	
	public float[][] generateSmoothNoiseCosinter (float[][] baseNoise, int octave, int size) {
		int width = size;
		int height = size;
		float[][] smoothNoise = new float[width][height];

		int samplePeriod = 1 << octave; // calculates 2 ^ k
		//int samplePeriod = octave*octave;
		//int samplePeriod = octave;
		
		float sampleFrequency = 1f / samplePeriod;
		for (int i = 0; i < width; i++) {
			int sample_i0 = (i / samplePeriod) * samplePeriod;
			int sample_i1 = (sample_i0 + samplePeriod) % width; // wrap around
			float horizontal_blend = (i - sample_i0) * sampleFrequency;

			for (int j = 0; j < height; j++) {
				int sample_j0 = (j / samplePeriod) * samplePeriod;
				int sample_j1 = (sample_j0 + samplePeriod) % height; // wrap around
				float vertical_blend = (j - sample_j0) * sampleFrequency;
				float top = cosInter(baseNoise[sample_i0][sample_j0], baseNoise[sample_i1][sample_j0], horizontal_blend);
				float bottom = cosInter(baseNoise[sample_i0][sample_j1], baseNoise[sample_i1][sample_j1], horizontal_blend);
				smoothNoise[i][j] = cosInter(top, bottom, vertical_blend);
				
			}
		}

		return smoothNoise;
	}

	/**
	 * @param baseNoise - pass whitenoise
	 * @param octaveCount - how many arrays
	 * @param octaves - array of which octaves you are passing
	 * @param amp - amplitude
	 * @param pers - persistance
	 * @return
	 */
	public float[][] generatePerlinNoise (float[][] baseNoise, int[] octaves, float amp, float pers, int size) {
		int width = size;
		int height = size;
		float[][][] smoothNoise = new float[octaves.length][][]; // an array of 2D arrays containing
		float persistance = pers;

		for (int i = 0; i < octaves.length; i++) {
			smoothNoise[i] = generateSmoothNoiseCosinter(baseNoise, octaves[i], size);
		}

		float[][] perlinNoise = new float[width][height]; // an array of floats initialised to 0

		float amplitude = amp;
		float totalAmplitude = 0.0f;

		for (int octave = octaves.length - 1; octave >= 0; octave--) {
			amplitude *= persistance;
			totalAmplitude += amplitude;

			for (int i = 0; i < width; i++) {
				for (int j = 0; j < height; j++) {
					perlinNoise[i][j] += smoothNoise[octave][i][j] * amplitude;
				}
			}
		}
		smoothNoise = null;
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				perlinNoise[i][j] /= totalAmplitude;
			}
		}

		return perlinNoise;
	}

	public float[][] generatePerlinNoiseInterpolate (float[][] baseNoise, int[] octaves, float amp, float pers, int size) {
		int width = size;
		int height = size;
		float[][][] smoothNoise = new float[octaves.length][][]; // an array of 2D arrays containing
		float persistance = pers;

		for (int i = 0; i < octaves.length; i++) {
			smoothNoise[i] = generateSmoothNoiseInterpolate(baseNoise, octaves[i], size);
		}

		float[][] perlinNoise = new float[width][height]; // an array of floats initialised to 0

		float amplitude = amp;
		float totalAmplitude = 0.0f;

		for (int octave = octaves.length - 1; octave >= 0; octave--) {
			amplitude *= persistance;
			totalAmplitude += amplitude;

			for (int i = 0; i < width; i++) {
				for (int j = 0; j < height; j++) {
					perlinNoise[i][j] += smoothNoise[octave][i][j] * amplitude;
				}
			}
		}
		smoothNoise = null;
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				perlinNoise[i][j] /= totalAmplitude;
			}
		}

		return perlinNoise;
	}
}