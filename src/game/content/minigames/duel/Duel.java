package game.content.minigames.duel;

import game.players.Player;

import java.util.Objects;

import utils.BitMask;

/**
 * Created with eclipse 26/03/2015 6:03:42 p. m.
 * 
 * @Author Juan Sebastian Quiceno <Juan.2114@hotmail.com>
 */
public class Duel {
	
	private BitMask rules = new BitMask();

	private final DuelSession session;
	
	public Duel(final DuelSession session) {
		this.session = session;
	}
	
	/*public static void main(String[] args) {
		final DuelSession duelSession = new DuelSession(null, null);
		final Duel duel = new Duel(duelSession);
		
		duel.mark(null, Rules.MELEE.buttonId);
		duel.mark(null, Rules.MAGIC.buttonId);
		duel.mark(null, Rules.RANGED.buttonId);
		duel.mark(null, Rules.RANGED.buttonId);

	}*/
	

	public void mark(final Player player, final int buttonId) {
		final Rules rules = Rules.forButton(buttonId);
		if (Objects.isNull(rules))
			return;

		rules.set(player, this);
		
		updateInterfaces(session.getPlayer(), 6575);
		updateInterfaces(session.getRival(), 6575);        
                    
	}

	public void updateInterfaces(final Player player, final int interfaceId) {
		if (interfaceId == 6575) {
			//int mask = rules.getMask();
			
			// if(rules.isMaskOn(1)) {
	               // player.encoder.sendString("Neither player is allowed to forfeit the duel.", 6684)
	           // } else {
	                //player.encoder.sendString("", 6684)
	           // }

	           // player.encoder.sendInterface 6575
	           // player.encoder.sendIntState(286, mask) // config
			// kept .. going
		}
	}

	public BitMask getRules() {
		return rules;
	}

	private enum Rules {
		FORFEIT(26065, 1, new RuleCondition() {

	        @Override
	        public boolean meets(final Player player, Duel duel, int value) {
	            return true;
	        }

	    }),
	    MOVEMENT(26066, 2, new RuleCondition() {

	        @Override
	        public boolean meets(final Player player, Duel duel, int value) {
	            boolean maskOn = duel.getRules().isMaskOn(9);
	            if (maskOn) {
	                //player.encoder.sendMessage "You cannot have no-movement and obsticals on at the same time."
	                return false;
	            }
	            return !maskOn;
	        }

	    }),
	    RANGED(26069, 16, new RuleCondition() {

	        @Override
	        public boolean meets(final Player player, Duel duel, int value) {
	            boolean maskOn = (duel.rules.isMaskOn(4) && duel.rules.isMaskOn(5));
	            if (maskOn) {
	                //player.encoder.sendMessage "You must leave at-least one combat type open!"
	                return false;
	            }
	            return !maskOn;
	        }

	    }),
	    MELEE(26070, 32, new RuleCondition() {

	        @Override
	        public boolean meets(final Player player, Duel duel, int value) {
	            boolean maskOn = (duel.rules.isMaskOn(3) && duel.rules.isMaskOn(5));
	            if (maskOn) {
	                //player.encoder.sendMessage "You must leave at-least one combat type open!"
	                return false;
	            }
	            return !maskOn;
	        }

	    }),
	    MAGIC(26071, 64, new RuleCondition() {

	        @Override
	        public boolean meets(final Player player, Duel duel, int value) {
	            boolean maskOn = (duel.rules.isMaskOn(3) && duel.rules.isMaskOn(4));
	            if (maskOn) {
	                //player.encoder.sendMessage "You must leave at-least one combat type open!"
	                return false;
	            }
	            return !maskOn;
	        }

	    }),
	    DRINKS(26072, 128, new RuleCondition() {

	        @Override
	        public boolean meets(final Player player, Duel duel, int value) {
	        	return true;
	        }

	    }),
	    FOOD(26073, 256, new RuleCondition() {

	        @Override
	        public boolean meets(final Player player, Duel duel, int value) {
	        	return true;
	        }

	    }),
	    PRAYER(26074, 512, new RuleCondition() {

	        @Override
	        public boolean meets(final Player player, Duel duel, int value) {
	        	return true;
	        }

	    }),
	    OBSTACLES(26076, 1024, new RuleCondition() {

	        @Override
	        public boolean meets(final Player player, Duel duel, int value) {
	            boolean maskOn = duel.getRules().isMaskOn(2);
	            if (maskOn) {
	                //player.encoder.sendMessage "You cannot have no-movement and obsticals on at the same time."
	                return false;
	            }
	            return !maskOn;
	        }

	    }),
	    FUN_WEAPONS(2158, 4096, new RuleCondition() {

	        @Override
	        public boolean meets(final Player player, Duel duel, int value) {
	           // player.encoder.sendMessage "Fun weapons are currently disabled."
	        	return false;
	        }

	    }),
	    SPECIAL_ATTACKS(30136, 8192, new RuleCondition() {

	        @Override
	        public boolean meets(final Player player, Duel duel, int value) {
	        	return true;
	        }

	    }),
	    DISABLE_HEAD(30136, 16384, new RuleCondition() {

	        @Override
	        public boolean meets(final Player player, Duel duel, int value) {
	        	return true;
	        }

	    }),
	    DISABLE_CAPE(30136, 32768, new RuleCondition() {

	        @Override
	        public boolean meets(final Player player, Duel duel, int value) {
	        	return true;
	        }

	    }),
	    DISABLE_AMULET(30136, 65536, new RuleCondition() {

	        @Override
	        public boolean meets(final Player player, Duel duel, int value) {
	        	return true;
	        }

	    }),
	    DISABLE_WEAPON(30136, 131072, new RuleCondition() {

	        @Override
	        public boolean meets(final Player player, Duel duel, int value) {
	        	return true;
	        }

	    }),
	    DISABLE_CHEST(30136, 262144, new RuleCondition() {

	        @Override
	        public boolean meets(final Player player, Duel duel, int value) {
	            return true;
	        }

	    }),
	    DISABLE_SHIELD(30136, 524288, new RuleCondition() {

	        @Override
	        public boolean meets(final Player player, Duel duel, int value) {
	            return true;
	        }

	    }),
	    DISABLE_LEGS(30136, 2097152, new RuleCondition() {

	        @Override
	        public boolean meets(final Player player, Duel duel, int value) {
	        	return true;
	        }

	    }),
	    DISABLE_HANDS(30136, 8388608, new RuleCondition() {

	        @Override
	        public boolean meets(final Player player, Duel duel, int value) {
	        	return true;
	        }

	    }),
	    DISABLE_FEET(30136, 16777216, new RuleCondition() {

	        @Override
	        public boolean meets(final Player player, Duel duel, int value) {
	        	return true;
	        }

	    }),
	    DISABLE_RINGS(30136, 67108864, new RuleCondition() {

	        @Override
	        public boolean meets(final Player player, Duel duel, int value) {
	        	return true;
	        }

	    }),
	    DISABLE_ARROWS(30136, 134217728, new RuleCondition() {

	        @Override
	        public boolean meets(final Player player, Duel duel, int value) {
	        	return true;
	        }

	    });
		private final int buttonId;
		private final int value;
		private final RuleCondition condition;

		Rules(final int buttonId, final int value, final RuleCondition condition) {
			this.buttonId = buttonId;
			this.value = value;
			this.condition = condition;
		}

		public static final Rules forButton(final int buttonId) {
			for (Rules rules : Rules.values()) {
				if (rules.buttonId == buttonId) {
					return rules;
				}
			}
			return null;
		}

		public boolean set(final Player player, final Duel duel) {
			return onSet(player, duel, condition, value);
		}
	}

	private static boolean onSet(final Player player, final Duel duel, final RuleCondition condition, final int value) {
		final boolean meets = condition.meets(player, duel, value);
		if (meets)
			duel.getRules().flip(value);
		return meets;
	}

	private interface RuleCondition {
		boolean meets(final Player player, final Duel duel, final int value);
	}
}
