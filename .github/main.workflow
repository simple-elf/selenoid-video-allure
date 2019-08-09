workflow "autotests" {
  on = "push"
  resolves = ["maven"]
}

action "maven" {
  uses = "LucaFeger/action-maven-cli@765e218a50f02a12a7596dc9e7321fc385888a27"
  runs = "mvn -f testng/pom.xml clean test -Dvideo.enabled=true -Dselenoid.url=http://127.0.0.1:4444/wd/hub"
}
