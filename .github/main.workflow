workflow "autotests" {
  on = "push"
  resolves = ["allure"]
}

action "test" {
  uses = "LucaFeger/action-maven-cli@master"
  runs = "mvn -f testng/pom.xml clean test -Dvideo.enabled=true -Dselenoid.url=http://167.71.60.248:4444/wd/hub"
}

action "allure" {
  uses = "LucaFeger/action-maven-cli@master"
  needs = ["test"]
  runs = "mvn -f testng/pom.xml allure:report"
}
