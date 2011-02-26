package com.eightbitmage.gdxlw;


public interface LibdgxWallpaperInterface {
	
	public void offsetChange (float xOffset, float yOffset,
			float xOffsetStep, float yOffsetStep, int xPixelOffset,
			int yPixelOffset);
	
	public void setIsPreview(boolean isPreview);

}
