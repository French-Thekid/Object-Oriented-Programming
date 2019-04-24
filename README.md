# Project Details

The Home & Away Institute (H&AI) is an Educational Institution that specializes in offering Distance
Learning courses. The institution has been in operation for over three (3) years, and has been using a paper
based system for the administration processing such as student admission, and programme administration.
The Chairman of the H&AI has requested that a program be created to automate the student admission and
programme administration processes. As the Manager of the Information and Communication Technology
(ICT) Department, you are in charge of presenting the prototype system at the next meeting of the Board
that is schedule for <b>November 7, 2016.</b>

After completing an analysis of the paper based system, the Chairman provided a set of based system
requirements. The Chairman has also instructed your team to provide additional functionalities to enhance
the program

# Initial requirements 
### The Prototype Should: ###
Provide an administrator account for the initial system configuration that allows the administrator to
create user accounts for staff members.
* Provide an administrator account for the initial system configuration that allows the administrator to
create user accounts for staff members.
* Provide staff member with a login screen for accessing the system
* Provide staff with menu options to:
    * Register Student
    * Programme Administration
      * Create Programme
      * Modify Programme Details
      * Generate Student List
* Provide students with a login screen for accessing the system
* Provide students with menu options to:
    * Enrol for Semester
      * View Programme Details
      * Add Course
      * Generate Fee Breakdown for Semester
    * Generate Progress Report
    
    
The analysis of the paper based system also uncovered details related to the various records held in a number
of files (i.e. staff, student, course, and programme), some of the information stored, includes:

Staff Record:
* Id Number, Name (First and Last), Faculty, Department and Date employed

Student Record:
* Id Number, Name (First and Last), Address, Date enrolled, Programme Code, Enrolment Status
and Contact number.

Course Record:
* Code, Name, Description, Credits and Pre-requisite


Programme Record:
* Code, Name, Maximum number of courses, Award and Accreditation

H&AI offers Certificate, Diploma and Associate Degree programmes. Each programme consists of a
number of courses.
* Certificate Programmes offer students four specialized courses
* Diploma Programmes offer student six specialized courses
* Associate Degree Programmes offer students eight specialized course

## Functionality Requirements, when the user selects:
<b>Register Student</b> – the system should present a form that accepts student’s details and generates a student
record. The student id number should be generated using the current year, programme code and a sequence
that starts at zero (0), and the enrolment status should be set to zero (0).

## Programme Administration:
<i>Create Programme</i> – the system should present a form that accepts general programme details.


<i>Modify Programme Details</i> – the system should present a form that allows for the addition, removal, editing
of courses for the programme.


Generate Student List – the system should present a form that accepts a programme code then displays the
list of student currently enrolled in the programme.

## Enrol for Semester
<i>View Programme Details</i> – the system should display all courses in the programme.

<i>Add Course</i> – the system should present a form that allows the selection of courses from the enrolled
programme.

<i>Generate Fee Breakdown for Semester<i> – the system should present a form that displays all courses enrolled
in for the semester and the total cost (i.e. number of credits * cost per credit), it should also switch students
enrolled status to one (1).


Generate Progress Report – the system should display all the courses the student has ever enrolled in for
the programme.
