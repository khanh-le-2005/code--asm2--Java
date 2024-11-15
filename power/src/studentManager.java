import java.util.ArrayList;
import java.util.Scanner;

public class studentManager {
    private ArrayList<student> students = new ArrayList<>();  // Đảm bảo dùng Student với chữ hoa
    private Scanner scanner = new Scanner(System.in);

    // Thêm sinh viên
    public void addStudent() {
        while (true) {
            try {
                // Nhập ID
                System.out.print("Enter Student ID: ");
                int id = 0;
                boolean validId = false;
                while (!validId) {
                    try {
                        id = Integer.parseInt(scanner.nextLine()); // Nhận ID như số nguyên
                        validId = true; // ID hợp lệ
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input! Please enter numeric values for ID.");
                        System.out.print("Enter Student ID: ");
                    }
                }

                // Nhập tên sinh viên (kiểm tra chữ cái)
                String name = "";
                while (true) {
                    System.out.print("Enter Student Name: ");
                    name = scanner.nextLine().trim();

                    // Kiểm tra xem tên có chỉ chứa chữ và khoảng trắng không
                    if (name.matches("[a-zA-Z ]+")) {
                        break; // Nếu hợp lệ, thoát khỏi vòng lặp
                    } else {
                        System.out.println("Invalid input! Please enter a valid name containing only letters and spaces.");
                    }
                }

                // Nhập điểm
                double marks = 0;
                while (true) {
                    System.out.print("Enter Student Marks: ");
                    try {
                        marks = Double.parseDouble(scanner.nextLine()); // Nhận điểm như số thực
                        if (marks >= 0 && marks <= 10) {
                            break; // Điểm hợp lệ
                        } else {
                            System.out.println("Invalid input! Marks should be between 0 and 10.");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input! Please enter numeric values for Marks.");
                    }
                }

                // Thêm sinh viên vào danh sách
                students.add(new student(id, name, marks));
                break; // Thoát khỏi vòng lặp nếu nhập hợp lệ

            } catch (Exception e) {
                // Xử lý ngoại lệ chung
                System.out.println("An error occurred: " + e.getMessage());
            }
        }
    }
    // Sửa thông tin sinh viên
    public void editStudent() {
        System.out.print("Enter Student ID to edit: ");
        int id = 0;
        boolean validId = false;
        while (!validId) {
            try {
                id = Integer.parseInt(scanner.nextLine()); // Nhận ID như số nguyên
                validId = true; // ID hợp lệ
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter numeric values for ID.");
                System.out.print("Enter Student ID to edit: ");
            }
        }

        // Tìm sinh viên theo ID
        student student = findStudentById(id);
        if (student != null) {
            System.out.println("Editing student: " + student);


            String name = "";
            while (true) {
                System.out.print("Enter new name: ");
                name = scanner.nextLine().trim();
                if (name.matches("[a-zA-Z ]+")) {
                    break;
                } else {
                    System.out.println("Invalid input! Please enter a valid name containing only letters and spaces.");
                }
            }
            double marks = 0;
            while (true) {
                System.out.print("Enter new marks: ");
                try {
                    marks = Double.parseDouble(scanner.nextLine());
                    if (marks >= 0 && marks <= 10) {
                        break;
                    } else {
                        System.out.println("Invalid input! Marks should be between 0 and 10.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input! Please enter numeric values for Marks.");
                }
            }
            student.setName(name);
            student.setMarks(marks);
            student.updateRank(); `

            System.out.println("Student updated: " + student);
        } else {
            System.out.println("Student not found with ID: " + id);
        }
    }
    // Xóa sinh viên
    public void deleteStudent() {
        System.out.print("Enter Student ID to delete: ");
        int id = Integer.parseInt(scanner.nextLine());

        student student = findStudentById(id);
        if (student != null) {
            students.remove(student);
            System.out.println("Student deleted: " + student);
        } else {
            System.out.println("Student not found with ID: " + id);
        }
    }

    // Tìm kiếm sinh viên theo ID
    public void searchStudentById() {
        System.out.print("Enter Student ID to search: ");
        int id = Integer.parseInt(scanner.nextLine());

        student student = findStudentById(id);
        if (student != null) {
            System.out.println("Student found: " + student);
        } else {
            System.out.println("Student not found with ID: " + id);
        }
    }

    // Tìm kiếm sinh viên theo tên
    public void searchStudentByName() {
        System.out.print("Enter Student Name to search: ");
        String name = scanner.nextLine();

        boolean found = false;
        for (student student : students) {
            if (student.getName().equalsIgnoreCase(name)) {
                System.out.println("Student found: " + student);
                found = true;
            }
        }

        if (!found) {
            System.out.println("No student found with name: " + name);
        }
    }

    // Hiển thị tất cả sinh viên
    public void displayStudents() {
        if (students.isEmpty()) {
            System.out.println("No students in the system.");
        } else {
            students.forEach(System.out::println);
        }
    }

    // Sắp xếp sinh viên theo điểm
    public void sortStudentsByMarks() {
        int n = students.size();
        for (int i = 0; i < n - 1; i++) {
            boolean swapped = false;
            for (int j = 0; j < n - i - 1; j++) {
                if (students.get(j).getMarks() < students.get(j + 1).getMarks()) {
                    student temp = students.get(j);
                    students.set(j, students.get(j + 1));
                    students.set(j + 1, temp);
                    swapped = true;
                }
            }
            if (!swapped) {
                break;
            }
        }
        System.out.println("Students sorted by marks:");
        displayStudents();
    }

    // Tìm sinh viên theo ID
    private student findStudentById(int id) {
        for (student student : students) {
            if (student.getId() == id) {
                return student;
            }
        }
        return null;
    }
}
