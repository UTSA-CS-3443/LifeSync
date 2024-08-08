# LifeSync

## Description
LifeSync is an all-in-one daily planner application designed to help you stay organized and manage your activities effectively. The app features a dynamic journal screen, a widget creation page, a notifications screen, user profile management, and a comprehensive calendar.

### Features

#### MainActivity Screen (Journal Screen)
The journal is utilized for keeping track of your planned-out events and reminders. 
- **Display Widgets:** Shows all widgets from today through to the next week, providing a clear view of your schedule and upcoming events.
- **Dynamic Loading:** Widgets are dynamically loaded and sorted by date and time to help you stay on top of your activities.
- **Time-Based Tracking:** Widgets are arranged chronologically, making it easy to track your activities by their scheduled time.

#### Widget Creation Page (Activity Screen)
Click on a day from the calendar and use this menu to plan out future events.
- **Task Creation:** Enter the title, description, start time, date, reminder, and repeat options for new tasks.
- **Event Creation:** Enter the title, description, start time, reminder time, date, and add participants for new events.
- **Note Creation:** Enter the title and description for new notes, which will automatically have the creation date as their default date.

#### Calendar (Calendar Screen)
- **Comprehensive View:** View all your past activities by selecting a day from a given month.

#### Notifications Screen
Check how long it is till your next event or receive notifications the day of.
- **Access Notifications:** Click the bell button at the top right corner to view upcoming events and deadlines.
- **Details:** See detailed descriptions, locations, guest lists, and start times for your events, tasks, and notes.
- **Deleting Widgets:** Easily delete a widget by clicking the trash can icon and confirming the deletion.

#### User Profile Management Screen
Fully customize your profile to make your experience even better.
- **Access Profile:** Click the user profile button at the top right corner to view basic user information.
- **Editable Profile:** Edit your display name, email, and biography to personalize your profile.

## Contributors
- Khoa Nguyen - backend coder
- Joshua Aaron - backend coder
- Dylan Fernandez - layout designer
- Brandon Barnes - tester and documenter

## Instructions for Running the Application

### Requirements
- Android Studio
- An Android device or emulator with internet access for syncing and updating information.

### Steps to Run
1. **Clone the Repository:** Clone the project repository from [GitHub](https://github.com/UTSA-CS-3443/LifeSync.git).
2. **Open in Android Studio:** Open the cloned project in Android Studio.
3. **Build the Project:** Allow Android Studio to build and sync the project.
4. **Run the Application:** Connect an Android device or start an emulator, then click the 'Run' button in Android Studio.

### Special Files
- Ensure you have internet access to clone the repository.

### How to Use
- The application will open directly to a page simulating the week. It will include Tasks, Notes, and Events (roughly 3-5 for each). To add a new Activity, click on the middle-bottom button. It will open a page where you can add a new Activity. Each Activity is added by type: Notes, Tasks, and Events.

## Known Issues
- Our program only reads and does not write.
- Our program only has a selected amount of dates; if run after our range, the application might be blank.
