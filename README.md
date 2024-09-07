# Hostel Management System 🏨

## Overview 🌟

The **Hostel Management System** is a comprehensive Java-based application designed to streamline hostel operations through a user-friendly graphical interface. Utilizing Object-Oriented Programming (OOP) principles and MySQL for data management, this system makes managing hostel records, room allocations, and tenant information efficient and straightforward.
![Screenshot from 2023-12-17 18-00-57](https://github.com/user-attachments/assets/ee5171bd-52b3-42a5-8fa5-a232a2f15b2b)
![Screenshot from 2023-12-17 17-59-35](https://github.com/user-attachments/assets/bafed8ff-5233-4ade-a339-fae66d843f61)
![Screenshot from 2023-12-17 17-57-40](https://github.com/user-attachments/assets/4ba9b298-1de2-446d-8f77-a31409f4b9a5)
![Screenshot from 2023-12-17 17-56-49](https://github.com/user-attachments/assets/236fcc54-fcbd-4921-8a4d-4e05435b81b9)
![Screenshot from 2023-12-17 17-55-26](https://github.com/user-attachments/assets/f91ecd40-7301-403b-820b-04b7988045f6)

## Features 🚀

- **Admin Login**: Secure access for administrators to manage hostel operations. 🔐
- **Attendance Tracking**: Record and track student attendance with ease. 📅
- **Complaints Management**: Handle and resolve student complaints effectively. 📝
- **Facilities Management**: Oversee and maintain hostel facilities. 🛠️
- **Leave Requests**: Process and manage student leave requests. 🏖️
- **Caretaker Management**: Manage caretaker schedules and details. 👨‍🏫👩‍🏫
- **Student Management**: Organize student data and room assignments. 🧑‍🎓
- **Mess Menu Management**: Update and manage the hostel's mess menu. 🍽️
- **Room Management**: Allocate and monitor room availability. 🛏️
- **Security Logs**: Maintain and review visitor logs and security records. 📜

## Project Structure 🗂️

- `src/` - Source code for the project.
- `out/` - Compiled classes and output directory.
- `.idea/` - IntelliJ IDEA project configuration files.
- `AdminLoginDialog/` - Components related to admin login functionality.
- `attendance/` - Components for managing attendance records.
- `complaints/` - Components for handling complaints.
- `Facilities/` - Components related to managing hostel facilities.
- `HostelManagementSystem/` - Core functionality and main application components.
- `leaverrequests/` - Components for handling leave requests.
- `ManageCaretakers/` - Components for managing caretaker data.
- `manageStudents/` - Components for managing student information.
- `messmenu/` - Components for managing the mess menu.
- `RoomManager/` - Components for room management.
- `Security/` - Components related to security and visitor logs.
- `visitorlog/` - Components for managing visitor logs.

## Prerequisites 🛠️

1. **Java Development Kit (JDK)**: Version 8 or later. Download from [Oracle](https://www.oracle.com/java/technologies/javase-jdk8-downloads.html).
2. **MySQL Database**: A running MySQL instance is required. Setup instructions can be found [here](https://dev.mysql.com/doc/mysql-installation-excerpt/5.7/en/).
3. **External Libraries**: Download the required JAR files from the following links and include them in your project's classpath:
   - [hamcrest-core-1.3.jar](https://search.maven.org/artifact/org.hamcrest/hamcrest-core/1.3/jar) 📦
   - [jcommon-1.0.23.jar](https://sourceforge.net/projects/jfreechart/files/jcommon/1.0.23/) 📦
   - [jfreechart-1.0.1.jar](https://sourceforge.net/projects/jfreechart/files/jfreechart/1.0.1/) 📦
   - [jfreechart-1.0.19-experimental.jar](https://sourceforge.net/projects/jfreechart/files/jfreechart/1.0.19/) 📦
   - [jfreechart-1.0.19-swt.jar](https://sourceforge.net/projects/jfreechart/files/jfreechart/1.0.19/) 📦
   - [jfreesvg-2.0.jar](https://sourceforge.net/projects/jfreesvg/files/jfreesvg/2.0/) 📦
   - [junit-4.11.jar](https://search.maven.org/artifact/junit/junit/4.11/jar) 📦
   - [mysql-connector-j-8.2.0.jar](https://dev.mysql.com/downloads/connector/j/) 📦
   - [orsoncharts-1.4-eval-nofx.jar](https://sourceforge.net/projects/orsoncharts/files/orsoncharts/1.4/) 📦
   - [orsonpdf-1.6-eval.jar](https://sourceforge.net/projects/orsonpdf/files/orsonpdf/1.6/) 📦
   - [servlet.jar](https://mvnrepository.com/artifact/javax.servlet/javax.servlet-api/4.0.1) 📦
   - [swtgraphics2d.jar](https://www.eclipse.org/swt/) 📦

## Installation ⚙️

1. **Setup the MySQL Database**:
    
    * Import the provided SQL file to create the necessary database schema:
        
      ```bash
      mysql -u yourusername -p yourdatabase < hostel_management_system.sql
      ```

2. **Add External Libraries**:
    
    * Download and include the required JAR files in your project's classpath. Add them to the `lib` directory of your project and update the build path in your IDE.



## Usage 📋

1. **Launch the Application**: Open the Hostel Management System GUI.
2. **Admin Login**: Log in using administrator credentials to access management features.
3. **Manage Hostel Operations**: Use the GUI to manage students, caretakers, rooms, complaints, and other hostel-related functions.

## Development 🤝

If you wish to contribute or modify the project:

1. **Fork the Repository**: Create your own fork on GitHub.
2. **Create a Branch**: Develop features or fix bugs on separate branches.
3. **Submit a Pull Request**: Once your changes are ready, submit a pull request for review.

## License 📜

This project is licensed under the MIT License 
## Contact 📬

For any questions or issues, please contact [mutinda.shadrack20@gmail.com](mailto:mutinda.shadrack20@gmail.com) or open an issue in the repository.
