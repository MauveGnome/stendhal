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
package games.stendhal.server;

import marauroa.server.game.*;

import marauroa.common.game.*;
import marauroa.common.*;
import games.stendhal.server.entity.*;

public class StendhalRPWorld extends RPWorld
  {
  public StendhalRPWorld() throws Exception
    {
    super();

    Logger.trace("StendhalRPWorld::StendhalRPWorld",">");
    createRPClasses();
    Logger.trace("StendhalRPWorld::StendhalRPWorld","<");
    }
  
  private void createRPClasses()
    {
    Logger.trace("StendhalRPWorld::createRPClasses",">");
    
    Entity.generateRPClass();

    Sign.generateRPClass();
    Portal.generateRPClass();
    Food.generateRPClass();
    
    RPEntity.generateRPClass();
    
    NPC.generateRPClass();
    BuyerNPC.generateRPClass();
    SellerNPC.generateRPClass();
    WelcomerNPC.generateRPClass();

    TrainingDummy.generateRPClass();

    Sheep.generateRPClass();
    Wolf.generateRPClass();
    Rat.generateRPClass();
    CaveRat.generateRPClass();

    Player.generateRPClass();
        
    Logger.trace("StendhalRPWorld::createRPClasses","<");
    }
  
  public void onInit() throws Exception
    {
    StendhalRPZone village=new StendhalRPZone("village");
    village.addLayer("village_0_floor","games/stendhal/server/maps/village_0_floor.stend");
    village.addLayer("village_1_object","games/stendhal/server/maps/village_1_object.stend");
    village.addLayer("village_2_roof","games/stendhal/server/maps/village_2_roof.stend");
    village.addCollisionLayer("village_collision","games/stendhal/server/maps/village_collision.stend");
    village.populate("games/stendhal/server/maps/village_objects.stend");
    addRPZone(village);


    StendhalRPZone city=new StendhalRPZone("city");
    city.addLayer("city_0_floor","games/stendhal/server/maps/city_0_floor.stend");
    city.addLayer("city_1_object","games/stendhal/server/maps/city_1_object.stend");
    city.addLayer("city_2_roof","games/stendhal/server/maps/city_2_roof.stend");
    city.addCollisionLayer("city_collision","games/stendhal/server/maps/city_collision.stend");
    city.populate("games/stendhal/server/maps/city_objects.stend");
    addRPZone(city);

    StendhalRPZone plains=new StendhalRPZone("plains");
    plains.addLayer("plains_0_floor","games/stendhal/server/maps/plains_0_floor.stend");
    plains.addLayer("plains_1_object","games/stendhal/server/maps/plains_1_object.stend");
    plains.addLayer("plains_2_roof","games/stendhal/server/maps/plains_2_roof.stend");
    plains.addCollisionLayer("plains_collision","games/stendhal/server/maps/plains_collision.stend");
    plains.populate("games/stendhal/server/maps/plains_objects.stend");
    addRPZone(plains);

    StendhalRPZone dungeon_000=new StendhalRPZone("dungeon_000");
    dungeon_000.addLayer("dungeon_000_0_floor","games/stendhal/server/maps/dungeon_000_0_floor.stend");
    dungeon_000.addLayer("dungeon_000_1_object","games/stendhal/server/maps/dungeon_000_1_object.stend");
    dungeon_000.addLayer("dungeon_000_2_roof","games/stendhal/server/maps/dungeon_000_2_roof.stend");
    dungeon_000.addCollisionLayer("dungeon_000_collision","games/stendhal/server/maps/dungeon_000_collision.stend");
    dungeon_000.populate("games/stendhal/server/maps/dungeon_000_objects.stend");
    addRPZone(dungeon_000);

//    StendhalRPZone test=new StendhalRPZone("test");
//    test.addLayer("test_0_floor","games/stendhal/server/maps/test_0_floor.stend");
//    test.addLayer("test_1_object","games/stendhal/server/maps/test_1_object.stend");
//    test.addLayer("test_2_roof","games/stendhal/server/maps/test_2_roof.stend");
//    test.addCollisionLayer("test_collision","games/stendhal/server/maps/test_collision.stend");
//    test.populate("games/stendhal/server/maps/test_objects.stend");
//    addRPZone(test);
    }
  
  public void onFinish() throws Exception
    {
    }
   
  }
