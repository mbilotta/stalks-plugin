package org.altervista.mbilotta.julia.impl.stalks;

import java.awt.Color;

import org.altervista.mbilotta.julia.IntermediateImage;
import org.altervista.mbilotta.julia.impl.FloatImage;
import org.altervista.mbilotta.julia.impl.PixelCalculator;


public class StalksPixelCalculator implements PixelCalculator {
	
	private final boolean orbitInsideOnly;
	private final Color trapColor;
	private final float[] trapColorRgba;
	private final Color background;
	
	private final float[] rgb;

	public StalksPixelCalculator(Stalks representation) {
		orbitInsideOnly = representation.isOrbitInsideOnly();
		trapColor = representation.getTrapColor();
		background = representation.getBackground();

		trapColorRgba = representation.getTrapColor().getRGBComponents(null);
		rgb = new float[3];
	}
	
	private static boolean isTranslucent(Color c) {
		return c.getAlpha() > 0 && c.getAlpha() < 255;
	}

	private Color multiply(float value) {
		rgb[0] = trapColorRgba[0] * value;
		rgb[1] = trapColorRgba[1] * value;
		rgb[2] = trapColorRgba[2] * value;

		return new Color(Math.min(rgb[0], 1), Math.min(rgb[1], 1), Math.min(rgb[2], 1), trapColorRgba[3]);
	}

	@Override
	public int getTransparency() {
		if (isTranslucent(trapColor) || (orbitInsideOnly && isTranslucent(background))) {
			return TRANSLUCENT;
		}
		if (trapColor.getAlpha() == 255 && (!orbitInsideOnly || background.getAlpha() == 255)) {
			return OPAQUE;
		}
		return BITMASK;
	}

	@Override
	public int computePixel(int x, int y, IntermediateImage iimg) {
		float value = ((FloatImage) iimg).getPoint(x, y);
		if (value == -1) {
			return background.getRGB();
		}
		return multiply(value).getRGB();
	}
}
