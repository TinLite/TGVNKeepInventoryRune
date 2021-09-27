# TGVNKeepInventoryRune

Phiên bản recode của KeepInventoryRune bởi GunFS

> ***Nếu UPDATE từ 1.0 lên 2.0, config.yml sẽ thay đổi. Hãy cập nhật sang config mới*** 

## GET THE JAR

### Dành cho server admin (configer gì đấy idk)

Thay vì tải source về làm lằng bà nhằng, bạn có thể [click vào đây](https://github.com/TinLite/TGVNKeepInventoryRune/releases/latest) để tải bản build sẵn.

### Dành cho những ai muốn tự build.

1. Clone hết đống source này về
2. `mvn clean package`
3. Vào `\target\` lấy file, hết

## Các loại Rune

### KeepInventoryRune

Giữ toàn bộ túi đồ người chơi, có mức độ ưu tiên cao nhất.  
Chỉ cần khi chết có Rune này trong túi đồ thì sẽ giữ lại túi đồ của người chơi đó. Mỗi lần chết trừ đi 1 Rune.  
Mức độ ưu tiên cao nhất.

### KeepItemRune

Rune này sẽ giữ lại một vật phẩm bất kì của người chơi do người chơi chỉ định.  
Khi chết, Rune này chỉ phát huy tác dụng khi KeepInventoryRune không tồn tại trong túi đồ.  
Sử dụng bằng cách kéo-thả rune vào item. Khi đó item sẽ có một dòng lore được chỉ định trong config.  
Khi chết, toàn bộ vật phẩm không có trạng thái bảo vệ sẽ bị rơi ra khỏi túi đồ. Riêng những vật phẩm có trạng thái bảo vệ sẽ vẫn được giữ lại ở vị trí như ban đầu trước khi chết. Vật phẩm được bảo vệ bởi Rune này sẽ mất đi trạng thái được bảo vệ, tức là mỗi lần áp dụng, chết, áp dụng lại mới được tiếp tục bảo vệ.

## Commands

Toàn bộ command đều yêu cầu quyền `kir.admin`.  
Nếu như không truyền tham số vào `[số lượng]`, thì lệnh sẽ mặc định hiểu số lượng là `1`  
Nếu như không truyền tham số vào `[người chơi]`, thì lệnh sẽ mặc định hiểu người chơi là chính người đang thực hiện lệnh

|Command|Mô tả|
|---|---|
|`/kir give <inventory/item> [số lượng] [người chơi]	`|Give Rune cho người chơi|
|`/kir removerune [người chơi]`|Gỡ KeepItemRune ra khỏi item đang cầm trên MainHand của người chơi|
|`/kir reload`|Reload plugin|
