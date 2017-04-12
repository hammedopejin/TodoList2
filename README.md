# TodoList2
A fully functional todolist app
# Pre-work - *TodoList*

TodoList is an android app that allows building a todo list and basic todo items management functionality including adding new items, editing and deleting an existing item.

Submitted by: Hammed Opejin

Time spent: 40 hours spent in total

## User Stories:
I spent some time trying to figure out how to use intent to pass data across activities. Also I used DialogFragment for delete option.

The following required functionality is completed:

[1] User can successfully add and remove items from the todo list
[2] User can tap a todo item in the list and bring up an edit screen for the todo item and then have any changes to the text reflected in the todo list.
[3] User can persist todo items and retrieve them properly on app restart

The following optional features are implemented:

[1] Persist the todo items [into SQLite](http://guides.codepath.com/android/Persisting-Data-to-the-Device#sqlite) instead of a text file
[2] Improve style of the todo items in the list [using a custom adapter](http://guides.codepath.com/android/Using-an-ArrayAdapter-with-ListView)
[3] Add support for completion due dates for todo items (and display within listview item)
[4] Use a [DialogFragment](http://guides.codepath.com/android/Using-DialogFragment) instead of new Activity for editing items
[5] Add support for selecting the priority of each todo item (and display in listview item)
[6] Tweak the style improving the UI / UX, play with colors, images or backgrounds

The following **additional** features are implemented:

[1] I made it impossible for user to select oudated date for due-date setting and editing.

## Video Walkthrough 

Here's a walkthrough of implemented user stories:

<img src='https://github.com/hammedopejin/TodoList2/blob/master/Todolist%20app%20demo.gif' title='Video Walkthrough' width='' alt='Video Walkthrough' />


GIF created with [LiceCap](http://www.cockos.com/licecap/).

## Notes

Fetching and setting todo and priority side by side poses some chalenges.

## License

    Copyright 2017 Hammed Opejin

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
