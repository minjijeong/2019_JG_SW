package study_hw;

import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class bj_15684_02 {
	
   static int N;
   static int M;
   static int H;
   static boolean isTrue;
   static boolean map[][];
   static Pos[] pos_set = new Pos[H*(N-1)];
   
   static class Pos{
       int x; int y;
       public Pos(int x, int y) {this.x = x; this.y = y;}
  }
   
   // i-i 연결여부 확인
   static boolean search(boolean[][] map){
	   
       boolean result = true;
       for(int n=1; n<=N; n++){
           int n_tmp = n;
           for(int h=1; h<=H; h++) {
               // 왼쪽으로 이동 (1은 확인x)
               if (n > 1 && map[h][n - 1] == true) {
                   --n;
              }// 오른쪽으로 이동 (N은 확인x)
               else if (n < N && map[h][n] == true){
                   ++n;
              }
          }
           if(n != n_tmp){
               result = false;
               break;
          }
           n = n_tmp;
      }
       return result;
  }
   static void combi_1(){
      boolean map_tmp[][] = new boolean[H+1][N];
       for(int i=1; i<=H; i++)
           for(int j=1; j<=N-1; j++)
               map_tmp[i][j] = map[i][j];
       
       for(int i=0; i<pos_set.length; i++){
           if(pos_set[i] != null){
               map_tmp[pos_set[i].x][pos_set[i].y] = true;
               if(search(map_tmp)){
                   isTrue = true;
                   break;
              }// 바뀐 맵 원래대로 돌려놓기
               map_tmp[pos_set[i].x][pos_set[i].y] = false;
          }
      }
  }
   
   static void combi_2(int arr[]){
       boolean map_tmp[][] = new boolean[H+1][N];
       for(int i=1; i<=H; i++)
           for(int j=1; j<=N-1; j++)
               map_tmp[i][j] = map[i][j];
       
       map_tmp[pos_set[arr[0]].x][pos_set[arr[0]].y] = true;
       map_tmp[pos_set[arr[1]].x][pos_set[arr[1]].y] = true;
       
       if(search(map_tmp))
           isTrue = true;
  }
   
   static void combi_3(int arr[]){
       boolean map_tmp[][] = new boolean[H+1][N];
       for(int i=1; i<=H; i++)
           for(int j=1; j<=N-1; j++)
               map_tmp[i][j] = map[i][j];
       
       map_tmp[pos_set[arr[0]].x][pos_set[arr[0]].y] = true;
       map_tmp[pos_set[arr[1]].x][pos_set[arr[1]].y] = true;
       map_tmp[pos_set[arr[2]].x][pos_set[arr[2]].y] = true;
       
       if(search(map_tmp))
           isTrue = true;
  }
   
   static void combination(int arr[], int index, int n, int r, int target){
       if(arr.length == 1) {
           combi_1();
      }else {
           if (r == 0) {
               if (arr.length == 2) { // 두 개 고르는 경우
                   combi_2(arr);
              } else if (arr.length == 3) { // 세 개 고르는 경우
                   combi_3(arr);
              }
          } else if (target == n) {
               return;
          } else {
               arr[index] = target;
               combination(arr, index + 1, n, r - 1, target + 1);
               combination(arr, index, n, r, target + 1);
          }
      }
  }
   
   public static void main(String[] args) throws IOException {
	   
       BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
       
       String input[] = in.readLine().split(" ");
       N = Integer.parseInt(input[0]);
       M = Integer.parseInt(input[1]);
       H = Integer.parseInt(input[2]);
       map= new boolean[H+1][N]; // 한 칸씩 크게 맵을 만들어 줌(H x N-1)
       
       for(int i=0; i<M; i++){
           String line[] = in.readLine().split(" ");
           map[Integer.parseInt(line[0])][Integer.parseInt(line[1])] = true;
      }
       
       isTrue = false;
       int result = 0;
       
       //pos_set = new Pos[H*(N-1)];
       ArrayList<Pos> pos_list = new ArrayList();
       int p_index = 0;
       for(int i=1; i<=H; i++)
           for(int j=1; j<=N-1; j++)
              if(map[i][j] == false)
                  pos_list.add(new Pos(i, j));
       pos_set = pos_list.toArray(new Pos[p_index]);
       
       // i-i 연결 여부 확인
       if(search(map) == false){ // 초기상태 확인
           ++result;
           int arr_1[] = new int[1];
           combination(arr_1,0, pos_set.length, 1, 0);
           if(isTrue == false){ // 가로선 1개 추가 후 확인
               ++result;
               int arr_2[] = new int[2]; // 가로선 2개 추가 후 확인
               combination(arr_2,0, pos_set.length, 2, 0);
               if(isTrue == false){
                   ++result;
                   int arr_3[] = new int[3];
                   combination(arr_3,0, pos_set.length, 3, 0);
                   if(isTrue == false)
                       result = -1;
              }
          }
      }
       
       System.out.println(result);
       
  }
}

