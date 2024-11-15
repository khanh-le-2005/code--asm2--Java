public class student {
    private int id;
    private String name;
    private double marks;
    private int rank;

    public student(int id, String name, double marks) {
        this.id = id;
        this.name = name;
        this.marks = marks;
        this.rank = calculateRank(marks);
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getMarks() {
        return marks;
    }

    public void setName(String name) {  // Thêm phương thức setName
        this.name = name;
    }

    public void setMarks(double marks) {
        this.marks = marks;
        this.rank = calculateRank(marks);  // Cập nhật rank khi thay đổi điểm
    }

    public int getRank() {
        return rank;
    }

    private int calculateRank(double marks) {
        // Bạn có thể thay đổi cách tính rank tùy theo yêu cầu
        if (marks >= 8) return 1;
        else if (marks >= 6) return 2;
        else return 3;
    }

    public void updateRank() {
        this.rank = calculateRank(this.marks);
    }

    @Override
    public String toString() {
        return "ID: " + id + ", Name: " + name + ", Marks: " + marks + ", Rank: " + rank;
    }
}
