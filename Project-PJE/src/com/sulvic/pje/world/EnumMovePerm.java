package com.sulvic.pje.world;

import com.sulvic.util.SulvicMath;

public enum EnumMovePerm{
	
	/**HEIGHT CROSSING*/
	PERM_00((byte)0x00),
	/**WALL*/
	PERM_01((byte)0x04),
	/**MOVEMENT PERMISSION 2*/
	PERM_02((byte)0x08),
	/**MOVEMENT PERMISSION 3*/
	PERM_03((byte)0x0C),
	/**WATER (HEIGHT 0)*/
	PERM_04((byte)0x10),
	/**WATER (HEIGHT 0) WALL*/
	PERM_05((byte)0x14),
	/**MOVEMENT PERMISSION 6*/
	PERM_06((byte)0x18),
	/**MOVEMENT PERMISSION 7*/
	PERM_07((byte)0x1C),
	/**HEIGHT 1*/
	PERM_08((byte)0x20),
	/**HEIGHT 1 WALL*/
	PERM_09((byte)0x24),
	/**MOVEMENT PERMISSION A*/
	PERM_0A((byte)0x28),
	/**MOVEMENT PERMISSION B*/
	PERM_0B((byte)0x2C),
	/**DEFAULT (HEIGHT 2)*/
	PERM_0C((byte)0x30),
	/**DEFAULT (HEIGHT 2) WALL*/
	PERM_0D((byte)0x34),
	/**MOVEMENT PERMISSION E*/
	PERM_0E((byte)0x38),
	/**MOVEMENT PERMISSION F*/
	PERM_0F((byte)0x3C),
	/**HEIGHT 3*/
	PERM_10((byte)0x40),
	/**HEIGHT 3 WALL*/
	PERM_11((byte)0x44),
	/**MOVEMENT PERMISSION 11*/
	PERM_12((byte)0x48),
	/**MOVEMENT PERMISSION 12*/
	PERM_13((byte)0x4C),
	/**HEIGHT 4*/
	PERM_14((byte)0x50),
	/**HEIGHT 4 WALL*/
	PERM_15((byte)0x54),
	/**MOVEMENT PERMISSION 16*/
	PERM_16((byte)0x58),
	/**MOVEMENT PERMISSION 17*/
	PERM_17((byte)0x5C),
	/**HEIGHT 5*/
	PERM_18((byte)0x60),
	/**HEIGHT 5 WALL*/
	PERM_19((byte)0x64),
	/**MOVEMENT PERMISSION 1A*/
	PERM_1A((byte)0x68),
	/**MOVEMENT PERMISSION 1B*/
	PERM_1B((byte)0x6C),
	/**HEIGHT 6*/
	PERM_1C((byte)0x70),
	/**HEIGHT 6 WALL*/
	PERM_1D((byte)0x74),
	/**MOVEMENT PERMISSION 1E*/
	PERM_1E((byte)0x78),
	/**MOVEMENT PERMISSION 1F*/
	PERM_1F((byte)0x7C),
	/**HEIGHT 7*/
	PERM_20((byte)0x80),
	/**HEIGHT 7 WALL*/
	PERM_21((byte)0x84),
	/**MOVEMENT PERMISSION 22*/
	PERM_22((byte)0x88),
	/**MOVEMENT PERMISSION 23*/
	PERM_23((byte)0x8C),
	/**HEIGHT 8*/
	PERM_24((byte)0x90),
	/**HEIGHT 8 WALL*/
	PERM_25((byte)0x94),
	/**MOVEMENT PERMISSION 26*/
	PERM_26((byte)0x98),
	/**MOVEMENT PERMISSION 27*/
	PERM_27((byte)0x9C),
	/**HEIGHT 9*/
	PERM_28((byte)0xA0),
	/**HEIGHT 9 WALL*/
	PERM_29((byte)0xA4),
	/**MOVEMENT PERMISSION 2A*/
	PERM_2A((byte)0xA8),
	/**MOVEMENT PERMISSION 2B*/
	PERM_2B((byte)0xAC),
	/**HEIGHT 10*/
	PERM_2C((byte)0xB0),
	/**HEIGHT 10 WALL*/
	PERM_2D((byte)0xB4),
	/**MOVEMENT PERMISSION 2E*/
	PERM_2E((byte)0xB8),
	/**MOVEMENT PERMISSION 2F*/
	PERM_2F((byte)0xBC),
	/**HEIGHT 11*/
	PERM_30((byte)0xC0),
	/**HEIGHT 11 WALL*/
	PERM_31((byte)0xC4),
	/**MOVEMENT PERMISSION 32*/
	PERM_32((byte)0xC8),
	/**MOVEMENT PERMISSION 33*/
	PERM_33((byte)0xCC),
	/**HEIGHT 12*/
	PERM_34((byte)0xD0),
	/**HEIGHT 12 WALL*/
	PERM_35((byte)0xD4),
	/**MOVEMENT PERMISSION 36*/
	PERM_36((byte)0xD8),
	/**MOVEMENT PERMISSION 37*/
	PERM_37((byte)0xDC),
	/**HEIGHT 13*/
	PERM_38((byte)0xE0),
	/**HEIGHT 13 WALL*/
	PERM_39((byte)0xE4),
	/**MOVEMENT PERMISSION 3A*/
	PERM_3A((byte)0xE8),
	/**MOVEMENT PERMISSION 3B*/
	PERM_3B((byte)0xEC),
	/**BRIDGE CROSSING*/
	PERM_3C((byte)0xF0),
	/**MOVEMENT PERMISSION 3D*/
	PERM_3D((byte)0xF4),
	/**MOVEMENT PERMISSION 3E*/
	PERM_3E((byte)0xF8),
	/**MOVEMENT PERMISSION 3F*/
	PERM_3F((byte)0xFC);
	
	final byte theValue;
	
	EnumMovePerm(byte value){ theValue = value; }
	
	public static int size(){ return values().length; }
	
	public static EnumMovePerm getPermission(int index){ return values()[SulvicMath.clampInt(index, 0, size())]; }
	
	public static EnumMovePerm getPermission(String hex){ return getPermission(Integer.parseInt(hex, 16)); }
	
}
