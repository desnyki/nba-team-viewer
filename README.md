# theScore "NBA Team Viewer" Interview Question
At theScore, we're always looking for intelligent and resourceful Android developers to join our growing team. To help us evaluate new talent, we have created this take-home interview question. This question should take you no more than a few hours.

**All candidates must complete this before the possibility of an in-person interview. During the in-person interview, your submitted project will be used as the base for questions.**

### Why a take-home interview?
In-person coding interviews can be stressful and can hide some people's full potential. A take-home gives you a chance work in a less stressful environment and showcase your talent.

We want you to be at your best and most comfortable.

### Understanding the problem
In this repo is the file [`input.json`](/input.json). It contains data about NBA Teams. Each entry contains the following information
* `id`: (Team's ID)
* `full_name`: (Team's name)
* `wins`: (Team's wins)
* `losses`: (Team's losses)
* `players`: (List of players on the team)
  * `first`: (Player's first name)
  * `last`: (Player's last name)
  * `position`: (Player's position)
  * `number`: (Player's jersey number)


```json
{
  "id": 1,
  "full_name": "Boston Celtics",
  "wins": 45,
  "losses": 20,
  "players": [
    {
      "id": 37729,
      "first_name": "Kadeem",
      "last_name": "Allen",
      "position": "SG",
      "number": 45
    },
  ]
}
```

##### Requirements
Your task is to create an Android application that has two screens; a Team list, and a Team page. Your solution may be written in Java or Kotlin (or both) whichever you feel most comfortable with.

* The Team List screen
  * Displays all of the teams contained within [`input.json`](/input.json) in alphabetical order
  * Each team's `full_name`, `wins`, and `losses` must be displayed
  * When the user clicks a team it should launch their team page
  * **Bonus:** Allow the user to sort the list by `wins` or `losses` as well as alphabetically
* The Team Page screen
  * Displays information about a specific Team selected from the Team List
  * The team's `full_name`, `wins`, and `losses` must be displayed
  * The team's roster must be displayed with each Player's `first_name`, `last_name`, `position`, `number`

### Submitting a solution
1. Download this repo
2. Complete the problem outlined in the **Requirements** section
3. In your personal public GitHub repo, create a new public repo with this implementation
4. Provide this link to your contact at theScore

We will evaluate you on your ability to solve the problem defined in the requirements section as well as your choice of design patterns, libraries, and general coding style.

### Help
If you have any questions regarding requirements, do not hesitate to email your contact at theScore for clarification.
