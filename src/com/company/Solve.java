package com.company;

import java.util.*;

public class Solve {


    void solve(char[][] arr){
        print(arr);
        Queue<Cord> q = new LinkedList<>();
        q.add(GetStart(arr, arr));
        Cord currentLow = null;
        while (!q.isEmpty()){
            Cord c = q.poll();
            if(c.isEnd() && (currentLow == null || c.count < currentLow.count))
                 currentLow = c;
            q.addAll(c.GetNeighbors());
        }
        changeAndPrintMap(currentLow, arr);
    }


    private void changeAndPrintMap(Cord c, char[][] arr) {
        c = c.last;
        while (c.last != null){
            arr[c.x][c.y] = '*';
            c = c.last;
        }
        print(arr);
    }

    private void print(char[][] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (char c1 : arr[i])
                System.out.print(c1 + " ");
            System.out.println();
        }
        System.out.println("____________________");
    }

    private Cord GetStart(char[][] arr, char[][] map) {
        for (int i = 0; i < arr.length; i++)
            for (int i1 = 0; i1 < arr[i].length; i1++)
                if(arr[i][i1] == 'S') return new Cord(i, i1, map,null, new HashSet<Cord>(), 0);
        return null;
    }

    public class Cord{
        int x;
        int y;
        char[][] map;
        Cord last;
        HashSet<Cord> visited;
        int count;
        private int dir[][] = new int[][]{{0,1},{0,-1},{1,0},{-1,0}};

        public Cord(int x, int y, char[][] map, Cord last, HashSet visited, int count) {
            this.x = x;
            this.y = y;
            this.map = map;
            this.last = last;
            this.visited = visited;
            this.count = count;
        }

        public boolean isValid(){
            if(x >= map.length || y >= map[0].length  || x < 0 || y < 0 || map[x][y] == '#') return false;
            return isEnd() || !visited.contains(this);

        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Cord cord = (Cord) o;
            return x == cord.x &&
                    y == cord.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }

        public List<Cord> GetNeighbors(){
            visited.add(this);
            ArrayList ans = new ArrayList();
            for (int[] ints : dir) {
                Cord c = new Cord(x + ints[0], y + ints[1] , map, this, visited , count +1);
                if(c.isValid()) ans.add(c);
            }
            return ans;
        }

        public boolean isEnd() {
            return map[x][y] == 'E';
        }
    }
}
