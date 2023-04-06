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
- As a user, I want to see my classes sorted alphabetically or by grade (not accomplished in GUI)
- As a user, I want to have my GPA for each class be calculated by itself in real time while I am taking a class (updates whenever I add a new grade).
- As a user, I want to have an account that I can change (eg. first name and last name).
- As a user, I want to be able to have the option to save all my grades and classes
- As a user, I want to be able to reload all my progress (grades and classes) from where I left off.

> ## Phase 4: Task 2
- You can add and remove courses, add marks to courses, change student information, and change course information.
- A sample is shown below: \
Thu Apr 06 15:22:46 PDT 2023 \
Student first name changed to: Bob\
Thu Apr 06 15:22:46 PDT 2023 \
Student last name changed to: Doe \
Thu Apr 06 15:23:06 PDT 2023 \
A new course has been added: CPSC 110 \
Thu Apr 06 15:23:22 PDT 2023 \
CPSC 110 has been changed to BIOL 121 \
Thu Apr 06 15:23:22 PDT 2023 \
BIOL 121's subject has been changed to Biology \
Thu Apr 06 15:23:22 PDT 2023 \
BIOL 121's teacher been changed to Lynn Norman \
Thu Apr 06 15:23:33 PDT 2023 \
A mark of 67.0% has been added to Assignments of BIOL 121 \
Thu Apr 06 15:23:40 PDT 2023 \
A mark of 90.0% has been added to Final of BIOL 121 \
Thu Apr 06 15:23:57 PDT 2023 \
A new course has been added: CPSC 313 \
Thu Apr 06 15:23:59 PDT 2023 \
A course has been removed: CPSC 313

> ## Phase 4: Task 3
- After drawing my UML diagram, it gave me a birds eye view of my project structure and gave me insights into what I 
could do to refactor my code. I realized that there is intense coupling to the model package, where so many classes
have an association to the student. I think that I could have implemented the singleton pattern and made a single
instance of a student, as this one student is referred to by many of my classes. This way, I could reduce
coupling and would not have to constantly pass in a student as a parameter down a chain of methods.

- My console-based program, which was essentially the first version of my project, had very low cohesion.
My UI class, namely the ‘GradeAir’ class, was more than 400 lines long and contained all the methods related to my
application. If I were to refactor it, I would separate it out into multiple different classes, namely, one for
methods related to the main homepage, course information, and account information.

- After learning about the Observer pattern, I realized that I could have much better designed my GUI. Currently, I
exit and make new windows each time a change is made to the student, but I could have easily implemented the observer
pattern so that the main homepage of my application could be an observer, along with a few of the corresponding
panels, while the observable would be the student. This way, anytime a change is made to the student, I could easily
have all the observers update by having them implement their own update method from an Observer interface with an
abstract update method. Making new windows each time is simply ineffective in the long run and introduces too many
complications in the long run, and would get really messy if the application gets too big, as it would be hard to
load new windows each time; updating it slightly when it is needed would be much better through using the
observer pattern here.

> ## Citations
- Used the [JsonSerializationDemo](https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo) project for data persistence provided by UBC.
- Used the [AlarmSystem](https://github.students.cs.ubc.ca/CPSC210/AlarmSystem) project for EventLogging provided by UBC. 