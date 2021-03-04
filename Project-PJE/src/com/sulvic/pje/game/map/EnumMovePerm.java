package com.sulvic.pje.game.map;

public enum EnumMovePerm{
	
	/**HEIGHT CROSSING*/
	MP_00((byte)0x00),
	/**WALL*/
	MP_01((byte)0x04),
	/**MOVEMENT PERMISSION 2*/
	MP_02((byte)0x08),
	/**MOVEMENT PERMISSION 3*/
	MP_03((byte)0x0C),
	/**WATER (HEIGHT 0)*/
	MP_04((byte)0x10),
	/**WATER (HEIGHT 0) WALL*/
	MP_05((byte)0x14),
	/**MOVEMENT PERMISSION 6*/
	MP_06((byte)0x18),
	/**MOVEMENT PERMISSION 7*/
	MP_07((byte)0x1C),
	/**HEIGHT 1*/
	MP_08((byte)0x20),
	/**HEIGHT 1 WALL*/
	MP_09((byte)0x24),
	/**MOVEMENT PERMISSION A*/
	MP_0A((byte)0x28),
	/**MOVEMENT PERMISSION B*/
	MP_0B((byte)0x2C),
	/**DEFAULT (HEIGHT 2)*/
	MP_0C((byte)0x30),
	/**DEFAULT (HEIGHT 2) WALL*/
	MP_0D((byte)0x34),
	/**MOVEMENT PERMISSION E*/
	MP_0E((byte)0x38),
	/**MOVEMENT PERMISSION F*/
	MP_0F((byte)0x3C),
	/**HEIGHT 3*/
	MP_10((byte)0x40),
	/**HEIGHT 3 WALL*/
	MP_11((byte)0x44),
	/**MOVEMENT PERMISSION 11*/
	MP_12((byte)0x48),
	/**MOVEMENT PERMISSION 12*/
	MP_13((byte)0x4C),
	/**HEIGHT 4*/
	MP_14((byte)0x50),
	/**HEIGHT 4 WALL*/
	MP_15((byte)0x54),
	/**MOVEMENT PERMISSION 16*/
	MP_16((byte)0x58),
	/**MOVEMENT PERMISSION 17*/
	MP_17((byte)0x5C),
	/**HEIGHT 5*/
	MP_18((byte)0x60),
	/**HEIGHT 5 WALL*/
	MP_19((byte)0x64),
	/**MOVEMENT PERMISSION 1A*/
	MP_1A((byte)0x68),
	/**MOVEMENT PERMISSION 1B*/
	MP_1B((byte)0x6C),
	/**HEIGHT 6*/
	MP_1C((byte)0x70),
	/**HEIGHT 6 WALL*/
	MP_1D((byte)0x74),
	/**MOVEMENT PERMISSION 1E*/
	MP_1E((byte)0x78),
	/**MOVEMENT PERMISSION 1F*/
	MP_1F((byte)0x7C),
	/**HEIGHT 7*/
	MP_20((byte)0x80),
	/**HEIGHT 7 WALL*/
	MP_21((byte)0x84),
	/**MOVEMENT PERMISSION 22*/
	MP_22((byte)0x88),
	/**MOVEMENT PERMISSION 23*/
	MP_23((byte)0x8C),
	/**HEIGHT 8*/
	MP_24((byte)0x90),
	/**HEIGHT 8 WALL*/
	MP_25((byte)0x94),
	/**MOVEMENT PERMISSION 26*/
	MP_26((byte)0x98),
	/**MOVEMENT PERMISSION 27*/
	MP_27((byte)0x9C),
	/**HEIGHT 9*/
	MP_28((byte)0xA0),
	/**HEIGHT 9 WALL*/
	MP_29((byte)0xA4),
	/**MOVEMENT PERMISSION 2A*/
	MP_2A((byte)0xA8),
	/**MOVEMENT PERMISSION 2B*/
	MP_2B((byte)0xAC),
	/**HEIGHT 10*/
	MP_2C((byte)0xB0),
	/**HEIGHT 10 WALL*/
	MP_2D((byte)0xB4),
	/**MOVEMENT PERMISSION 2E*/
	MP_2E((byte)0xB8),
	/**MOVEMENT PERMISSION 2F*/
	MP_2F((byte)0xBC),
	/**HEIGHT 11*/
	MP_30((byte)0xC0),
	/**HEIGHT 11 WALL*/
	MP_31((byte)0xC4),
	/**MOVEMENT PERMISSION 32*/
	MP_32((byte)0xC8),
	/**MOVEMENT PERMISSION 33*/
	MP_33((byte)0xCC),
	/**HEIGHT 12*/
	MP_34((byte)0xD0),
	/**HEIGHT 12 WALL*/
	MP_35((byte)0xD4),
	/**MOVEMENT PERMISSION 36*/
	MP_36((byte)0xD8),
	/**MOVEMENT PERMISSION 37*/
	MP_37((byte)0xDC),
	/**HEIGHT 13*/
	MP_38((byte)0xE0),
	/**HEIGHT 13 WALL*/
	MP_39((byte)0xE4),
	/**MOVEMENT PERMISSION 3A*/
	MP_3A((byte)0xE8),
	/**MOVEMENT PERMISSION 3B*/
	MP_3B((byte)0xEC),
	/**BRIDGE CROSSING*/
	MP_3C((byte)0xF0),
	/**MOVEMENT PERMISSION 3D*/
	MP_3D((byte)0xF4),
	/**MOVEMENT PERMISSION 3E*/
	MP_3E((byte)0xF8),
	/**MOVEMENT PERMISSION 3F*/
	MP_3F((byte)0xFC);
	
	final byte permissionValue;
	
	EnumMovePerm(byte value){ permissionValue = value; }
	
	public byte getValue(){ return permissionValue; }
	
	public String toString(){ return Integer.toHexString((int)permissionValue / 4).toUpperCase(); }
	
	public static EnumMovePerm getPermission(byte trueId){ return values()[trueId / 4]; }
	
	public static EnumMovePerm getPermission(char ch){ return values()[Integer.parseInt(ch + "", 16)]; }
	
}
