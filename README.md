# Java-BackEnd
# :books: GitFlow Workflow

1- Pull codebase : `git clone https://github.com/ELBouch/Java-BackEnd.git`<br><br>
2- Open terminal / bash and install gitflow : `sudo apt-get install git-flow`<br><br>
3- Open terminal in the project directory and init the gitflow workflow then leave everything to default : `git flow init`<br><br>
4- Checkout to develop branch : `git checkout develop`<br><br>
5- Start working on your feature : `git flow feature start feature_branch`<br><br>
6- Continue your work and use Git like you normally would<br><br>
7- When you’re done with the development work on the feature, the next step is to push your changes in the branch you created using git flow : <br>
`git add .` then `git commit -m ":emoji: your commit message"` then `git push`<br><br>
8- After that, on the github page you select your branch and do a pull request to the <b>develop branch</b><br><br>
9- ``git flow feature start feature_branch`` can be replaced by ``git flow hotfix start hotfix_branch`` depending on what your task is (feature, release, hotfix, support)<br><br>
 10- <b>Make sure that your develop branch is always up to date before you create a new feature</b><br><br>

<span style="color:red">**NEVER PUSH TO MAIN DIRECTLY**</span>

# 🕺 commit-emojis

| Commit type                | Emoji                                                     |
| :------------------------- | :-------------------------------------------------------- |
| Initial commit             | :tada: `:tada:`                                           |
| Version tag                | :bookmark: `:bookmark:`                                   |
| New feature                | :sparkles: `:sparkles:`                                   |
| Bugfix                     | :bug: `:bug:`                                             |
| Metadata                   | :card_index: `:card_index:`                               |
| Documentation              | :books: `:books:`                                         |
| Documenting source code    | :bulb: `:bulb:`                                           |
| Performance                | :racehorse: `:racehorse:`                                 |
| Cosmetic                   | :lipstick: `:lipstick:`                                   |
| Tests                      | :rotating_light: `:rotating_light:`                       |
| Adding a test              | :white_check_mark: `:white_check_mark:`                   |
| Make a test pass           | :heavy_check_mark: `:heavy_check_mark:`                   |
| General update             | :zap: `:zap:`                                             |
| Improve format/structure   | :art: `:art:`                                             |
| Refactor code              | :hammer: `:hammer:`                                       |
| Removing code/files        | :fire: `:fire:`                                           |
| Continuous Integration     | :green_heart: `:green_heart:`                             |
| Security                   | :lock: `:lock:`                                           |
| Upgrading dependencies     | :arrow_up: `:arrow_up:`                                   |
| Downgrading dependencies   | :arrow_down: `:arrow_down:`                               |
| Lint                       | :shirt: `:shirt:`                                         |
| Translation                | :alien: `:alien:`                                         |
| Text                       | :pencil: `:pencil:`                                       |
| Critical hotfix            | :ambulance: `:ambulance:`                                 |
| Deploying stuff            | :rocket: `:rocket:`                                       |
| Fixing on MacOS            | :apple: `:apple:`                                         |
| Fixing on Linux            | :penguin: `:penguin:`                                     |
| Fixing on Windows          | :checkered_flag: `:checkered_flag:`                       |
| Work in progress           | :construction: `:construction:`                           |
| Adding CI build system     | :construction_worker: `:construction_worker:`             |
| Analytics or tracking code | :chart_with_upwards_trend: `:chart_with_upwards_trend:`   |
| Removing a dependency      | :heavy_minus_sign: `:heavy_minus_sign:`                   |
| Adding a dependency        | :heavy_plus_sign: `:heavy_plus_sign:`                     |
| Docker                     | :whale: `:whale:`                                         |
| Configuration files        | :wrench: `:wrench:`                                       |
| Package.json in JS         | :package: `:package:`                                     |
| Merging branches           | :twisted_rightwards_arrows: `:twisted_rightwards_arrows:` |
| Bad code / need improv.    | :hankey: `:hankey:`                                       |
| Reverting changes          | :rewind: `:rewind:`                                       |
| Breaking changes           | :boom: `:boom:`                                           |
| Code review changes        | :ok_hand: `:ok_hand:`                                     |
| Accessibility              | :wheelchair: `:wheelchair:`                               |
| Move/rename repository     | :truck: `:truck:`                                         |
| Other                      | [Be creative](http://www.emoji-cheat-sheet.com/)          |

<!-- LINKS -->

[license]: https://github.com/sebald/commit-emojis/blob/master/LICENCE
[license-badge]: https://img.shields.io/npm/l/commit-emojis.svg?style=flat-square
[package]: https://www.npmjs.com/package/commit-emojis
[version-badge]: https://img.shields.io/npm/v/commit-emojis.svg?style=flat-square
