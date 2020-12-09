//Guilherme Alves Vieira

import java.awt.Color;
import java.util.Arrays;
import java.util.Collections;

public class JogoMemoria {
	private Color[] subPaleta = new Color[16];
	private Color[][] cores = new Color[4][4];
	private String[][] marcadores = new String[4][4];
	private int maxTentativas;
	private int acertos;
	
	public JogoMemoria(Color[] paleta) {
		//Inicialização da matriz de marcadores
		for(int loop=0; loop<4; loop++) {
			for(int secLoop=0; secLoop<4; secLoop++) {
				marcadores[loop][secLoop] = "- ";
			}
		}
		
		//Inserção e embaralhamento dos pares de cores na matriz
		for(int loop=0; loop<8; loop++) {
			subPaleta[loop] = paleta[loop];
		}
		int count=0;
		for(int loop=8; loop<16; loop++) {
			subPaleta[loop] = paleta[count];
			count++;
		}
		
		
		Collections.shuffle(Arrays.asList(subPaleta));
		
		count=0;
		for(int row=0; row<4; row++) {
			for(int col=0; col<4; col++) {
				cores[row][col] = subPaleta[count];
				count++;
				if(count==16) {break;}
			}
		}
		
	}
	
	public boolean adivinhar (int linha1, int coluna1, int linha2, int coluna2) throws Exception {
			if((linha1 >= 0 && linha1 <= 3) && (coluna1 >= 0 && coluna2 <= 3) && 
				(linha2 >= 0 && linha2 <= 3) && (coluna2 >= 0 && coluna2 <= 3)){
				if(cores[linha1][coluna1] == cores[linha2][coluna2]) {
					marcadores[linha1][coluna1]="X";
					marcadores[linha2][coluna2]="X";
					acertos++;
					maxTentativas++;
					return true;
				}else {
					maxTentativas++;
					return false;
				}
			}else {
				throw new Exception("Posição Inválida\n");
			}
	}
	
	public boolean terminou() {
		if(acertos==8 || maxTentativas==30) {
			return true;
		}else {
			return false;
		} 
	}
	
	public Color getCor(int linha, int coluna) throws Exception{
		if(cores[linha][coluna] != null) {
			return cores[linha][coluna];
		}else {
			throw new Exception("Posição Inválida\n");
		}
	}
	
	public String getResultadoFinal() {
		if(acertos==8) {
			return "acertou com " + maxTentativas + " tentativas";
		}else {
			return "terminou com " + maxTentativas + " tentativas";
		}
	}
	
	public int getAcertos() {
		return acertos;
	}
	
	public int getTentativas() {
		return maxTentativas;
	}
	
	@Override
	public String toString(){
		String res="";
		for(int loop=0; loop<4; loop++) {
			for(int secLoop=0; secLoop<4; secLoop++) {
				res += marcadores[loop][secLoop] + ", ";
			}
		}
		return res;
	}
}	
