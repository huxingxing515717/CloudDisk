
本项目基于[莫提网盘](https://github.com/373675032/moti-cloud) 进行二次开发
- 增加在线预览功能，主要针对代码文件进行在线预览
- java代码提供在线编译系统，在线编译后台已完成单还未整合，稍后进行整合
- 提供markdown自动分页功能，使其易于在浏览器上进行浏览（需要后台上传文件，此处由于前端不会写，没办法）


使用：
请修改所有sample文件后进行部署

2020.3.7
- 将jquery.contextMenu.min.js中的 top: t.clientY + 2改为 top: t.clientY + $(window).scrollTop()+2以修复当页面下拉时出现菜单显示位置错误
2020.3.6
- 修改前端ebook界面，使得界面左右可以分别滑动，侧边栏上下滑动，左右超出区域分行显示，且侧边栏以contentid作为id(之前是header)
- 后端重新改了数据结构，改为普通的树结构

2020.3.6
- 增加在线编译模块（仅支持java）

2020.3.6
- 将所有的css、js改为cdn加速，加速网站访问速度

2020.3.5
- 修改下载逻辑，不利用应用服务器进行转存，节省应用服务器流量并且提高大文件下载响应度
