import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solver {

    private ArrayList<ArrayList<Integer>> board;

    public Solver(ArrayList<ArrayList<Integer>> board) {
        this.board = new ArrayList<>(board.size());
        for (int i = 0; i < board.size(); i++) {
            this.board.add(new ArrayList<>(board.get(i)));
        }
    }

    public static boolean is_distinct(List<Integer> list)
    {
        List<Integer> used = new ArrayList<>();
        for(int element : list)
        {
            if(used.contains(element) && element != 0)
            {
                return false;
            }
            used.add(element);
        }
        return true;
    }

    public static boolean is_valid(ArrayList<ArrayList<Integer>> board)
    {
        for(int i = 0; i < board.size(); i++)
        {
            List<Integer> row = List.of(new Integer[]{board.get(i).get(0), board.get(i).get(1), board.get(i).get(2)});
            if(!is_distinct(row))
            {
                return false;
            }
            List<Integer> column = List.of(new Integer[]{board.get(0).get(i), board.get(1).get(i), board.get(2).get(i)});
            if(!is_distinct(column))
            {
                return false;
            }
        }
        return true;
    }

    public boolean solve(int empty) {
        if (empty == 0) {
            return is_valid(this.board);
        }

        for (int row = 0; row < this.board.size(); row++) {
            for (int col = 0; col < this.board.size(); col++) {
                int cell = this.board.get(row).get(col);
                if (cell == 0) {
                    for (int test = 1; test <= 3; test++) {
                        this.board.get(row).set(col, test);
                        if (is_valid(this.board) && solve(empty - 1)) {
                            return true;
                        }
                        this.board.get(row).set(col, 0);
                    }
                    return false;
                }
            }
        }
        return false;
    }


    public void display_board()
    {
        System.out.println("    +---+---+---+");
        for(ArrayList<Integer> line : board)
        {
            System.out.print("    |");
            for(int a : line)
            {
                System.out.print(" " + a + " ");
                System.out.print("|");
            }
            System.out.print("\n");
            System.out.println("    +---+---+---+");
        }
    }


    public static void main(String [] args)
    {
        ArrayList<ArrayList<Integer>> board = new ArrayList<ArrayList<Integer>>() {{
            add(new ArrayList<>(List.of(0, 0, 0)));
            add(new ArrayList<>(List.of(0, 2, 0)));
            add(new ArrayList<>(List.of(0, 0, 1)));
        }};
        Solver s = new Solver(board);
        System.out.println("PLATEAU DE SUDOKU (4X4) avant résolution : ");
        s.display_board();
        s.solve( 7);
        System.out.println("PLATEAU DE SUDOKU (4X4) après résolution : ");
        s.display_board();
    }

}
