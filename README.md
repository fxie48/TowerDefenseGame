Git workflow

1. git checkout {your working branch}  // ex) git checkout jason
2. git pull                            // before push your change, please pull first
3. git add                             // Selects your file which you make change, and moves it to the staging area
                                       // ex) git add Configuration.java or git add . -> when you want to add all changed files
4. git commit -m "Your message"        // Captures a snapshot of the project's currently staged changes
5. (optional) git tag -a {version name} "Your message"
6. git push                            // push your files to remote repo (please do after git pull)
7. (optional) git push origin --tags
8. Make a PR
9. Merge your code after someone reviews
10. git checkout main                   // after merging your code into remote branch
11. git pull                            // make your local repo updated
12. git checkout {your working branch} // Keep working on your code
