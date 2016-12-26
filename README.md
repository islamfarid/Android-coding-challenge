# Android Coding Challenge
We want to know how you write **code** - we don't care about coding challenges where you have to reimplement the HTTP protocol by using the bare basics, we want to know how you can use the existing libraries to solve the problems that we have to solve.

Your task is to create a very small Android application which will listen to a voice of the user and will tell them the current weather information when asked for. It's up to you to decide how exactly you want to approach this challenge - do you want to use the native SpeechRecognizer for voice recognition, use offline voice recognition for keywords (e.g. PocketSphinx) or another online service with conversational capabilities (e.g. api.ai, Microsoft LUIS, etc.). You can also choose whichever weather service you want.

## What we'll look at
- Structure of the code - how you use controllers, services, views. Keep it clean and reusable.
- Code formatting, included unit and Espresso tests.
- Using external APIs is cool, but you have to make sure the app will support errors if the API is down.
- Readme - we don't need documentation, but a small file explaining how to run the project will be useful.
- Overall user experience in the application.

## Time limit
It should take you between 4 to 8 hours to finish this task. If it takes you longer, it's okay to leave TODOs in the code, just provide an explanation what you would still finish there.

After sending the challenge we'll wait 2 weeks to hear back from you. Feel free to ask us for any clarification if you need it.

## Process
When you're ready, please fork this repository and start writing code in your fork. You'll get extra points for committing often in small chunks, so we'll see the process of how you created the application.
##############################################################################################
#islam comments
kindly be notified that i am using MVP architecture pattern, generally i divide the app packages to business names i mean if we have business called add book to library there will be package with name addbooktolib and if there
exist another functionality for removing book there will be another one called removebookfrom lib inside every package there will be View [activity + fragment] , presenter and business
and also dagger subpackage for dependencey injection
# i didn't make all possible unit testing but i tried to show you my way of thinking also there exist an issue that they run individually correct but together they may fail may be i am using shared resource and didn't reset
but i don't have time to search about this issue i am sorry for that
one last thing i ran out of time so i douldn't make unit testing for the busness class