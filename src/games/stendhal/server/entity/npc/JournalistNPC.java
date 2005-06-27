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
package games.stendhal.server.entity.npc;

import games.stendhal.common.*;
import marauroa.common.*;
import marauroa.common.game.*;
import marauroa.server.game.*;
import games.stendhal.server.*;
import java.util.*;

import games.stendhal.server.entity.*;

/** TODO: Complete this class funcitionality */
public class JournalistNPC extends SpeakerNPC
  {
  final private double SPEED=0.5;

  public static void generateRPClass()
    {
    try
      {
      RPClass npc=new RPClass("journalistnpc");
      npc.isA("npc");
      npc.add("text",RPClass.LONG_STRING, RPClass.VOLATILE);
      }
    catch(RPClass.SyntaxException e)
      {
      Logger.thrown("JournalistNPC::generateRPClass","X",e);
      }
    }

  public JournalistNPC() throws AttributeNotFoundException
    {
    super();
    put("type","journalistnpc");
    put("name","Brian");
    }

  protected void createPath()
    {
    List<Path.Node> nodes=new LinkedList<Path.Node>();
    nodes.add(new Path.Node(17,12));
    nodes.add(new Path.Node(17,13));
    nodes.add(new Path.Node(16,8));
    nodes.add(new Path.Node(13,8));
    nodes.add(new Path.Node(13,6));
    nodes.add(new Path.Node(13,10));
    nodes.add(new Path.Node(23,10));
    nodes.add(new Path.Node(23,10));
    nodes.add(new Path.Node(23,13));
    nodes.add(new Path.Node(23,10));
    nodes.add(new Path.Node(17,10));
    setPath(nodes,true);
    }

  protected boolean chat(Player player) throws AttributeNotFoundException
    {
    String text=player.get("text").toLowerCase();
    if(text.contains("hi"))
      {
      say("Hi visitor! May I ask you some questions to help us improve Stendhal? (yes/no)");
      return true;
      }
    else if(text.contains("job") || text.contains("help"))
      {
      say("I run polls to improve the quality of Stendhal.");
      return true;
      }
    else if(text.equals("no"))
      {
      say("Oh! :(");
      return true;
      }

    return false;
    }
  }
