/* $Id$ */
/***************************************************************************
 *                      (C) Copyright 2003 - Marauroa                      *
 ***************************************************************************
 ***************************************************************************
 *                                                                         *
 *   This program is free software; you can redistribute it and/or modify  *
 *   it under the terms of the GNU General Public License as published by  *
 *   the Free Software Foundation; either version 2 of the License, or     *
 *   (at your option) any later version.                                   *
 *                                                                         *
 ***************************************************************************/
package games.stendhal.client.entity;

import marauroa.common.game.*;
import games.stendhal.client.*;
import java.awt.*;
import java.awt.geom.*;

public class Sign extends GameEntity 
  {
  private final static int TEXT_PERSISTENCE_TIME=10000;
  private Sprite textImage;
  private GameScreen screen;
  private long delta;
  
  public Sign(RPObject object) throws AttributeNotFoundException
    {    
    super(object);
    
    screen=GameScreen.get();
    }
  
  public void modify(RPObject object) throws AttributeNotFoundException
    {
    super.modify(object);
    if(object.has("text") && textImage==null)
      {
      String text=object.get("text");
      System.out.println ("Rendering text: "+text);
      Graphics g2d=screen.expose();

      int lineLengthPixels=g2d.getFontMetrics().stringWidth(text);
      
      GraphicsConfiguration gc = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration();
      int width=lineLengthPixels+4;
      int height=16;
      
      Image image = gc.createCompatibleImage(width,height,Transparency.BITMASK);
      
      Graphics g=image.getGraphics();
      g.setColor(Color.white);
      g.fillRect(0,0,width,height);
      g.setColor(Color.black);
      g.drawRect(0,0,width-1,height-1);
            
      g.setColor(Color.black);
      g.drawString(text,2,12);
        
      textImage=new Sprite(image);      
      }
    }
    
  public void onLeftClick()
    {
    delta=System.currentTimeMillis();
    }

  public void draw(GameScreen screen)
    {
    super.draw(screen);
    
    if(textImage!=null && System.currentTimeMillis()-delta<TEXT_PERSISTENCE_TIME) 
      {
      screen.draw(textImage,x+0.5-(textImage.getWidth()/(32.0f*2.0f)),y);
      }
    }
  }
