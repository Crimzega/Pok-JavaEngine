package com.sulvic.pje.exceptions;

@SuppressWarnings({"serial"})
public class GameEngineException extends RuntimeException{
	
	public GameEngineException(String msg){ super(msg); }
	
	public GameEngineException(String msg, Throwable thrown){ super(msg, thrown); }
	
}
