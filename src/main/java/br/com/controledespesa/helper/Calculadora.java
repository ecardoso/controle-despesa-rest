package br.com.controledespesa.helper;

import java.math.BigDecimal;
import java.util.Collection;

public class Calculadora {

	public static final int ESCALA_PRECISAO_BAIXA = 2;
	public static final int ESCALA_PRECISAO_ALTA = 12;

	public static final BigDecimal ZERO = new BigDecimal("0.00");
	public static final BigDecimal UM = new BigDecimal("1.00");
	public static final BigDecimal CEM = new BigDecimal("100.00");

	public static final BigDecimal VALOR_SIGNIFICATIVO = new BigDecimal("0.01");

	private Calculadora() {
		super();
	}

	public static BigDecimal defaultBigDecimal(Object valor) {
		if (valor instanceof BigDecimal) {
			return defaultBigDecimal(valor);
		}

		return BigDecimal.ZERO;
	}

	public static BigDecimal defaultBigDecimal(BigDecimal valor) {
		return BigDecimalUtils.defaultBigDecimal(valor, new BigDecimal("0.00"));
	}

	public static Double defaultDouble(Object valor) {
		if (valor instanceof Double) {
			return defaultDouble((Double) valor);
		}

		return 0d;
	}

	public static Double defaultDouble(Double valor) {
		return valor == null || valor.equals(Double.NaN) ? 0d : valor;
	}

	public static Long defaultLong(Long valor) {
		return valor == null ? 0L : valor;
	}

	public static Short defaultShort(Short valor) {
		return valor == null ? Short.valueOf("0") : valor;
	}

	public static BigDecimal definirPrecisao(BigDecimal valor, int escala, int arredondamento) {
		if (valor == null) {
			return null;
		}

		valor = defaultBigDecimal(valor);
		return valor.divide(UM, escala, arredondamento);
	}

	public static BigDecimal definirPrecisaoPadrao(BigDecimal valor) {
		return definirPrecisao(valor, ESCALA_PRECISAO_BAIXA, BigDecimal.ROUND_HALF_UP);
	}

	public static boolean isSignificativo(BigDecimal valor) {
		if (valor == null) {
			return false;
		}

		return valor.compareTo(VALOR_SIGNIFICATIVO) >= 0;
	}

	public static Double somar(Double op1, Double op2) {
		BigDecimal valor1 = BigDecimal.valueOf(defaultDouble(op1));
		BigDecimal valor2 = BigDecimal.valueOf(defaultDouble(op2));

		return somar(valor1, valor2).doubleValue();
	}

	public static Short somar(Short op1, Short op2) {
		Short valor1 = defaultShort(op1);
		Short valor2 = defaultShort(op2);

		return somar(new BigDecimal(valor1), new BigDecimal(valor2)).shortValue();
	}

	public static Long somar(Long op1, Long op2) {
		Long valor1 = defaultLong(op1);
		Long valor2 = defaultLong(op2);

		return valor1 + valor2;
	}

	public static BigDecimal somar(BigDecimal op1, BigDecimal op2) {
		op1 = defaultBigDecimal(op1);
		op2 = defaultBigDecimal(op2);

		return op1.add(op2);
	}

	public static BigDecimal somar(BigDecimal... values) {
		BigDecimal total = BigDecimal.ZERO;
		for (BigDecimal bigDecimal : values) {
			BigDecimal value = BigDecimalUtils.defaultBigDecimal(bigDecimal);
			total = total.add(value);
		}

		return total;
	}

	public static BigDecimal somar(Collection<BigDecimal> values) {
		return somar(values.toArray(new BigDecimal[values.size()]));
	}

	public static Double subtrair(Double op1, Double op2) {
		BigDecimal valor1 = BigDecimal.valueOf(defaultDouble(op1));
		BigDecimal valor2 = BigDecimal.valueOf(defaultDouble(op2));

		return subtrair(valor1, valor2).doubleValue();
	}

	public static BigDecimal subtrair(BigDecimal op1, BigDecimal op2) {
		op1 = defaultBigDecimal(op1);
		op2 = defaultBigDecimal(op2);

		return op1.subtract(op2);
	}

	public static BigDecimal multiplicar(BigDecimal op1, BigDecimal op2) {
		op1 = defaultBigDecimal(op1);
		op2 = defaultBigDecimal(op2);

		return op1.multiply(op2).divide(UM, ESCALA_PRECISAO_ALTA, BigDecimal.ROUND_HALF_UP);
	}

	public static BigDecimal multiplicar(BigDecimal op1, BigDecimal op2, int escala) {
		op1 = defaultBigDecimal(op1);
		op2 = defaultBigDecimal(op2);

		return op1.multiply(op2).divide(UM, escala, BigDecimal.ROUND_HALF_UP);
	}

	public static BigDecimal dividir(BigDecimal op1, BigDecimal op2) {
		op1 = defaultBigDecimal(op1);
		op2 = defaultBigDecimal(op2);

		return op1.divide(op2, ESCALA_PRECISAO_ALTA, BigDecimal.ROUND_HALF_UP);
	}

	public static BigDecimal dividirTruncando(BigDecimal op1, BigDecimal op2) {
		op1 = defaultBigDecimal(op1);
		op2 = defaultBigDecimal(op2);

		return op1.divide(op2, ESCALA_PRECISAO_BAIXA, BigDecimal.ROUND_DOWN);
	}

	public static BigDecimal percentual(BigDecimal op1) {
		if (op1 == null) {
			return BigDecimal.ZERO;
		}

		return op1.divide(CEM, ESCALA_PRECISAO_ALTA, BigDecimal.ROUND_HALF_UP);
	}

	public static BigDecimal exponencial(BigDecimal op1, int op2) {
		double base = op1.doubleValue();
		double resultado = Math.pow(base, op2);

		return BigDecimal.valueOf(resultado).divide(UM, ESCALA_PRECISAO_ALTA, BigDecimal.ROUND_HALF_UP);
	}

	public static BigDecimal logaritmoNatural(BigDecimal baseLog) {
		double base = baseLog.doubleValue();
		double resultado = Math.log(base);

		return BigDecimal.valueOf(resultado).divide(Calculadora.UM, Calculadora.ESCALA_PRECISAO_ALTA, BigDecimal.ROUND_HALF_UP);
	}

	public static BigDecimal restoDivisao(double vlrFinanceiroTotal, double vlrMultiplo) {
		return BigDecimal.valueOf(vlrFinanceiroTotal).setScale(ESCALA_PRECISAO_BAIXA, BigDecimal.ROUND_HALF_UP)
								.remainder(BigDecimal.valueOf(vlrMultiplo).setScale(ESCALA_PRECISAO_BAIXA, BigDecimal.ROUND_HALF_UP));
	}
}
