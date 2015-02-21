package br.com.util;

import java.util.Random;

public class Utilidades {
	public static long gerarCodigo() {
		Random r = new Random();
		String aleatorio = Integer.toString(
				100000000 + Math.abs(r.nextInt(999999999))).substring(0, 6);
		return (long) Long.parseLong(aleatorio);
	}

}
