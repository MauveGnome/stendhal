package games.stendhal.server.maps.semos.village;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import games.stendhal.server.core.engine.StendhalRPWorld;
import games.stendhal.server.core.engine.StendhalRPZone;
import games.stendhal.server.entity.npc.ConversationStates;
import games.stendhal.server.entity.npc.NPCList;
import games.stendhal.server.entity.npc.SpeakerNPC;
import games.stendhal.server.entity.npc.fsm.Engine;
import games.stendhal.server.entity.player.Player;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import utilities.PlayerTestHelper;
import utilities.QuestHelper;

/**
 * Test buying sheep.
 *
 * @author Martin Fuchs
 */
public class SheepSellerNPCTest {

	private static final String ZONE_NAME = "0_semos_village_w";

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		QuestHelper.setUpBeforeClass();

		StendhalRPZone zone = new StendhalRPZone(ZONE_NAME);
		StendhalRPWorld world = StendhalRPWorld.get();
		world.addRPZone(zone);

		SheepSellerNPC seller = new SheepSellerNPC();
		seller.configureZone(zone, null);
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
		SpeakerNPC npc = NPCList.get().get("Nishiya");
		if (npc != null) {
			npc.setCurrentState(ConversationStates.IDLE);
		}
	}

	@Test
	public void testHiAndBye() {
		Player player = PlayerTestHelper.createPlayer("player");

		SpeakerNPC npc = NPCList.get().get("Nishiya");
		assertNotNull(npc);
		Engine en = npc.getEngine();

		assertTrue(en.step(player, "hello"));
		assertEquals("Greetings! How may I help you?", npc.get("text"));

		assertTrue(en.step(player, "bye"));
		assertEquals("Bye.", npc.get("text"));
	}

	@Test
	public void testBuySheep() {
		Player player = PlayerTestHelper.createPlayer("player");

		StendhalRPWorld world = StendhalRPWorld.get();
		PlayerTestHelper.registerPlayer(player, world.getZone(ZONE_NAME));

		SpeakerNPC npc = NPCList.get().get("Nishiya");
		assertNotNull(npc);
		Engine en = npc.getEngine();

		assertTrue(en.step(player, "hi"));
		assertEquals("Greetings! How may I help you?", npc.get("text"));

		assertTrue(en.step(player, "job"));
		assertEquals("I work as a sheep seller.", npc.get("text"));

		assertTrue(en.step(player, "offer"));
		assertEquals("I sell sheep.", npc.get("text"));

		assertTrue(en.step(player, "buy"));
		assertEquals("Please tell me what you want to buy.", npc.get("text"));

		assertTrue(en.step(player, "buy dog"));
		assertEquals("Sorry, I don't sell dogs.", npc.get("text"));

		assertTrue(en.step(player, "buy house"));
		assertEquals("Sorry, I don't sell houses.", npc.get("text"));

		assertTrue(en.step(player, "buy someunknownthing"));
		assertEquals("Sorry, I don't sell someunknownthings.", npc.get("text"));

		assertTrue(en.step(player, "buy sheep"));
		assertEquals("1 sheep will cost 30. Do you want to buy it?", npc.get("text"));

		assertTrue(en.step(player, "no"));
		assertEquals("Ok, how else may I help you?", npc.get("text"));

		assertTrue(en.step(player, "buy sheep"));
		assertEquals("1 sheep will cost 30. Do you want to buy it?", npc.get("text"));

		assertTrue(en.step(player, "yes"));
		assertEquals("You don't seem to have enough money.", npc.get("text"));

		// equip with enough money to buy one sheep
		assertTrue(PlayerTestHelper.equipWithMoney(player, 30));

		assertTrue(en.step(player, "buy 2 sheep"));
		assertEquals("2 sheep will cost 60. Do you want to buy them?", npc.get("text"));

		assertTrue(en.step(player, "yes"));
		assertEquals("Hmm... I just don't think you're cut out for taking care of a whole flock of sheep at once.", npc.get("text"));

		assertTrue(en.step(player, "buy sheep"));
		assertEquals("1 sheep will cost 30. Do you want to buy it?", npc.get("text"));

		assertFalse(player.hasSheep());

		assertTrue(en.step(player, "yes"));
		assertEquals("Here you go, a nice fluffy little sheep! Take good care of it, now...", npc.get("text"));

		assertTrue(player.hasSheep());
	}

	@Test
	public void testSellSheep() {
		Player player = PlayerTestHelper.createPlayer("player");

		SpeakerNPC npc = NPCList.get().get("Nishiya");
		assertNotNull(npc);
		Engine en = npc.getEngine();

		assertTrue(en.step(player, "hi Nishiya"));
		assertEquals("Greetings! How may I help you?", npc.get("text"));

		assertTrue(en.step(player, "sell"));
		assertEquals("Once you've gotten your sheep up to a weight of 100, you can take her to Sato in Semos; he will buy her from you.", npc.get("text"));

		assertTrue(en.step(player, "sell sheep"));
		assertEquals("Once you've gotten your sheep up to a weight of 100, you can take her to Sato in Semos; he will buy her from you.", npc.get("text"));
	}

}
