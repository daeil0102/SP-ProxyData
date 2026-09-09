<h1>SP-ProxyData</h1>

<p>해당 프로젝트는 프록시용 서버에서 데이터를 쉽게 처리하는 프로젝트 입니다</p>
<p>해당 플러그인을 사용하기 위해선 SP-Framework가 필요합니다</p>
<p>해당 프로젝트는 미완성된 프로젝트 입니다 일부 오류가 있을 수 있습니다</p>

<h2>플러그인 버전</h2>

- 플러그인 : spigot 1.12+

<h2>라이센스</h2>

Copyright (c) 2026 Teujaem

1. 상업적 이용이 가능합니다.
2. 2차 수정이 불가능 합니다. (fork 포함)
3. 2차 배포가 불가능 합니다.

<h2>디스코드</h2>
https://discord.gg/QyuCbDs6nG

<h2>config</h2>

<p>bukkit/plugins/SP-ProxyData/config.yml</p>

```
# 해당 서버를 표시할 이름
# 작성 안할 시 폴더이름 작성됨
name: "server name"

broadcast:
  # 지원하는 형식
  # {server} - 해당 커맨드를 실행한 서버이름
  # {local_server} - 이 서버이름
  # {message} - 커맨드로 실행한 메세지
  style: "&a[ {server} ] {message}"

datalink:
  inventory: true
  ender-chest: true
  level: true
  hp: true
  food: true
  world: true
  location: true
  gamemode: true
```