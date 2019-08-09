workflow "autotests" {
  on = "push"
  resolves = ["test"]
}

action "test" {
  uses = "LucaFeger/action-maven-cli@master"
  runs = "mvn -f testng/pom.xml clean test allure:report"
}
