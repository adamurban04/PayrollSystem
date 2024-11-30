# CS4013 Software Development Project

**University of Limerick**  
Contact: [Michael English](mailto:Michael.English@ul.ie)

---

## Objectives

- Apply an object-oriented approach to the design and development of a software application in Java.
- Utilize concepts and techniques from lectures, tutorials, and labs to design and develop a small software application.

---

## Project: UL Payroll System

### System Requirements

Develop a Payroll system for UL Staff. Reference to full-time employee salary scales can be found [here](https://www.ul.ie/hr/current-staff/pay-benefits/salary-information), with hourly scales for roles like Lecture, Tutorial, Lab, Demo, Exam Invigilator, and Exam Supervisor. Part-time employees must submit a pay claim by the second Friday of each month for payment that month. The system should generate payslips for full-time staff and hourly-paid staff on the 25th of each month.

#### Key Requirements
- **Net Pay Calculation**: Deduct Health Insurance, Union Fees, PRSI, USC, Income Tax (see [Revenue.ie](https://www.revenue.ie) for tax info).
- **Annual Promotion**: In October, full-time staff move up a scale point if not at the top. Promotions to a new scale may apply based on years at the previous scale’s top point.
- **Data Storage**: All relevant data (e.g., employee details, payroll, payslips, etc.) should be stored as CSV files.
  
#### User Roles
1. **Employee**: Login to view details and payslips.
2. **Admin**: Login to add new employees.
3. **HR**: Login to manage promotions, with employee confirmation.

**Interface**: A CLI is required, with a well-defined separation from the interface layer to allow easy substitution of a GUI in the future.

---

## Project Suggestions

Consider adopting Agile techniques like Scrum for team management, such as 2-3 day progress meetings. Track contributions in a `contributions.md` file.

---

## Deliverables

1. **Class Responsibility Collaboration (CRC) Document**: Outline CRC cards in a word document.
2. **UML Class Diagram**: Show relationships (inheritance, aggregation, dependency) between classes using [draw.io](https://www.draw.io) or similar.
3. **Documentation**: Use `javadoc` for software documentation and include a help file with basic usage instructions.
4. **Source Code**: Java classes and interfaces in separate files, organized by packages.
5. **CSV Files**: Include sample data for testing.
6. **GitHub Repository**: Use for version control.
7. **Contributions File**: Document team contributions in `contributions.md`.

---

## Rules and Regulations

- **Group Work**: Max group size of 4. Add group details to the [project group spreadsheet](cs4013ProjectGroups2425.xlsx) by **Sunday, Nov. 3rd**.
- **Project Demo**: Each member must give a code walkthrough and demonstrate their code at an assigned time. Failure to present may result in an F-grade.
- **Plagiarism**: Strictly prohibited, including uncredited GenAI tools use. An F-grade will be given for in-term assessments if plagiarism is detected.
- **Submission**: Submit the project as a compressed file on Brightspace by **12:00 PM, Monday, Week 13**. Late submissions incur a 10% daily penalty and will not be accepted after 12:00 PM, Thursday, Week 13.

*Note*: Loss of work is not an accepted excuse. Manage your time effectively.

---
