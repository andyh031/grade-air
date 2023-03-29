> # GradeAir
>
> #### *A Grading Application by Andy Hu*
>
Does it ever annoy you when you have no way of recalling how you did on 
that one test, or how you did on that final exam? Transcripts only show 
the final grade, but what if you wanted to see how you actually got that 
grade? This application will store all of your grades in a friendly and
aesthetically pleasing user-interface so that you can look back and see
how you performed in all of your classes, down to each assignment you
handed in and each test you wrote. This application is meant to be used 
as a grade tracker, where you upload grades while the course is still 
in progress, and it will store these grades after the year is over
and you start a new school year. Likewise, the primary target audience
for this application would be students currently in school. 

This project interests me because I have sometimes found myself handwriting
test scores I achieved in certain classes, since only the final grade would
remain on my transcript after I move up a grade. Not only that, the way that
your grades are displayed on a transcript is visually unappealing. These
factors motivated me to write an app that students, including me, could 
use to keep track of all of their grades in a more visually appealing way,
while simultaneously capturing the grades that went underneath the hood
to yield a final grade. 

> ## User Stories
- As a user, I want to add classes to my list of classes
  - As a user, I want to give traits to each of these classes (teacher, subject, year taken, weighting scheme)
- As a user, I want to add grades(marks/scores) to each of my classes
- As a user, I want to see my classes sorted alphabetically or by grade
- As a user, I want to have my GPA for each class be calculated by itself in real time while I am taking a class (updates whenever I add a new grade).
- As a user, I want to have an account that I can change (eg. first name and last name).
- As a user, I want to be able to have the option to save all my grades and classes
- As a user, I want to be able to reload all my progress (grades and classes) from where I left off.

> ## Citations
- Used the JsonSerializationDemo project provided by UBC.

> ## Instructions for Grader
- You can generate the first required action related to adding Xs to a Y by pressing the "Add" button on the homescreen,
where you will be prompted to input information about the class.
- You can generate the second required action related to adding Xs to a Y by pressing the "Remove Class" button on
the home screen, while is a toggle that will allow you to click a class and remove it.
- You can locate my visual component on the home screen where I have hand drawn the logo and profile,
and you can also hover over classes and see them change colour.
- You can save the state of my application by pressing save on the home screen.
- You can load the state of my application when prompted to 'register' for the application.