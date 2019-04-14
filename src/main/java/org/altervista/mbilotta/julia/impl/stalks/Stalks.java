package org.altervista.mbilotta.julia.impl.stalks;

import java.awt.Color;

import org.altervista.mbilotta.julia.Author;
import org.altervista.mbilotta.julia.Decimal;
import org.altervista.mbilotta.julia.IntermediateImage;
import org.altervista.mbilotta.julia.NumberFactory;
import org.altervista.mbilotta.julia.Previewable;
import org.altervista.mbilotta.julia.Progress;
import org.altervista.mbilotta.julia.impl.AbstractSimpleRepresentation;
import org.altervista.mbilotta.julia.impl.FloatImage;
import org.altervista.mbilotta.julia.impl.PixelCalculator;
import org.altervista.mbilotta.julia.impl.PointCalculator;
import org.altervista.mbilotta.julia.impl.RasterImage;
import org.altervista.mbilotta.julia.math.CoordinateTransform;
import org.altervista.mbilotta.julia.math.Real;


@Author(name = "Maurizio Bilotta", contact = "mailto:maurizeio@gmail.com")
@Author(name = "J. P. Botelho", contact = "https://twitter.com/noob_studios")
public class Stalks extends AbstractSimpleRepresentation {
	
	private int maxIterations;
	private int minIterations;
	private Color trapColor;
	private Color background;

	private Real orbitPositionX;
	private Real orbitPositionY;
	private Real orbitPositionZ;
	private Real orbitPositionW;
	
	private Real orbitDividend;
	
	private boolean orbitInsideOnly;
	private boolean showOrbitX;
	private boolean showOrbitY;
	
	public Stalks() {
		super(5);
		minIterations = 1;
		maxIterations = 100;
		trapColor = new Color(0f, .25f, 1f);
		background = new Color(1f, 1f, 1f);
		
		orbitPositionX = Decimal.ZERO;
		orbitPositionY = Decimal.ZERO;
		orbitPositionZ = Decimal.ZERO;
		orbitPositionW = Decimal.ZERO;

		orbitDividend = new Decimal("0.05");
		
		orbitInsideOnly = false;
		showOrbitX = true;
		showOrbitY = true;
	}

	@Override
	protected RasterImage createIntermediateImage(int width, int height, Progress[] progress) {
		return new FloatImage(width, height, progress);
	}

	@Override
	protected PointCalculator createPointCalculator(IntermediateImage iImg, NumberFactory numberFactory,
			CoordinateTransform coordinateTransform, boolean isJuliaSet) {
		FloatImage floatImg = (FloatImage) iImg;
		return isJuliaSet ?
				new StalksCalculator.JuliaSet(floatImg, this, numberFactory) :
				new StalksCalculator.MandelbrotSet(floatImg, this, numberFactory);
	}

	@Override
	protected PixelCalculator createPixelCalculator() {
		return new StalksPixelCalculator(this);
	}

	public int getMaxIterations() {
		return maxIterations;
	}

	public int getMinIterations() {
		return minIterations;
	}

	public Color getTrapColor() {
		return trapColor;
	}

	public Color getBackground() {
		return background;
	}

	public Real getOrbitPositionX() {
		return orbitPositionX;
	}

	public Real getOrbitPositionY() {
		return orbitPositionY;
	}

	public Real getOrbitPositionZ() {
		return orbitPositionZ;
	}

	public Real getOrbitPositionW() {
		return orbitPositionW;
	}

	public Real getOrbitDividend() {
		return orbitDividend;
	}

	public boolean isOrbitInsideOnly() {
		return orbitInsideOnly;
	}

	public boolean isShowOrbitX() {
		return showOrbitX;
	}

	public boolean isShowOrbitY() {
		return showOrbitY;
	}

	public void setMaxIterations(int maxIterations) {
		this.maxIterations = maxIterations;
	}

	public void setMinIterations(int minIterations) {
		this.minIterations = minIterations;
	}

	@Previewable
	public void setTrapColor(Color trapColor) {
		this.trapColor = trapColor;
	}

	@Previewable
	public void setBackground(Color background) {
		this.background = background;
	}

	public void setOrbitPositionX(Real orbitPositionX) {
		this.orbitPositionX = orbitPositionX;
	}

	public void setOrbitPositionY(Real orbitPositionY) {
		this.orbitPositionY = orbitPositionY;
	}

	public void setOrbitPositionZ(Real orbitPositionZ) {
		this.orbitPositionZ = orbitPositionZ;
	}

	public void setOrbitPositionW(Real orbitPositionW) {
		this.orbitPositionW = orbitPositionW;
	}

	public void setOrbitDividend(Real orbitDividend) {
		this.orbitDividend = orbitDividend;
	}

	public void setOrbitInsideOnly(boolean orbitInsideOnly) {
		this.orbitInsideOnly = orbitInsideOnly;
	}

	public void setShowOrbitX(boolean showOrbitX) {
		this.showOrbitX = showOrbitX;
	}

	public void setShowOrbitY(boolean showOrbitY) {
		this.showOrbitY = showOrbitY;
	}
}
