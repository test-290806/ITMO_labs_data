import java.util.Scanner;
import java.util.Random;
import static java.lang.Math.*;

class Main {
	public static void main(String[] args){
		short[] a = new short[16];
		
		for(short i = 19; i >= 4; i--){
			a[i - 4] = i;
		}
		
		double[] x = new double[10];
		
		//Создаём объект модуля Random для генерации псевдослучайных чисел
		Random rand = new Random(42);
		
		//Заполняем маасив x псевдослучайными вещественными числами
		for(int i = 0; i < 10; i++) x[i] = rand.nextDouble(-4., 8.);
		
		double c[][] = new double[16][10];
		for(int i = 0; i < 16; i++){
			for(int j = 0; j < 10; j++){
				c[i][j] = switch (a[i]){
					case 16 -> atan(pow(sin(x[j]), 2));
					
					case 4, 6, 7, 8, 9, 10, 12, 19 -> pow(0.25 / (pow(0.25 * pow(x[j] / 2., 3), 2)), atan(1. / exp(abs(x[j]))));
					
					default -> pow(pow(cbrt(tan(x[j])) * (1. / 3. - log(pow((abs(x[j])) / (2. * PI + abs(x[j])), 2))), 2),
					pow(cbrt(cbrt(x[j])), (cbrt(asin((x[j] + 2.) / 12.) - 1.)) / (exp(exp(x[j]))) + PI));
				};
			}
		}
		
		for(int i = 0; i < 16; i++){
			for(int j = 0; j < 10; j++){
				System.out.printf("%-15.5f", c[i][j]);
			}
			System.out.print("\n\n");
		}
	}
}
