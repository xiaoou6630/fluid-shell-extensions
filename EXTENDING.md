# Fluid Shell Extensions - Extension Development Guide

**This guide is currently only available in Chinese.**  
If you need an English version, please use a browser translator or contact the author.

---
# 娴佷綋寮规晥鏋滄墿灞?- 寮€鍙戞暀绋?

## 绠€浠?

娴佷綋寮规晥鏋滄墿灞曪紙Fluid Shell Extensions锛夋槸涓€涓负 Minecraft 1.20.1 Forge 鐨?Create Big Cannons 妯＄粍寮€鍙戠殑闄勫睘 Mod锛屽畠鍏佽浣犻€氳繃 JSON 閰嶇疆鏂囦欢鏉ヨ嚜瀹氫箟浠绘剰娴佷綋鐨勬晥鏋滐紝鑰屾棤闇€淇敼浠ｇ爜銆?

鏈暀绋嬪皢甯姪浣犱簡瑙ｅ浣曚娇鐢?JSON 閰嶇疆鏂囦欢鏉ユ坊鍔犲拰鑷畾涔夋祦浣撴晥鏋滐紝璁╀綘鐨勬祦浣撶偖寮规洿鍔犲鏍峰寲鍜屾湁瓒ｃ€?

## 閰嶇疆鏂囦欢浣嶇疆

閰嶇疆鏂囦欢浣嶄簬 `config/fluid_shell_extensions/effects/` 鐩綍涓嬶紝姣忎釜娴佷綋涓€涓?`.json` 鏂囦欢銆傛枃浠跺悕寤鸿涓?`鍛藉悕绌洪棿_璺緞.json`锛屼緥濡?`create_chocolate.json`銆?

## JSON 鏍煎紡璇﹁В

### 鍩烘湰缁撴瀯

```json
{
  "enabled": true,
  "fluid": "create:chocolate",
  "stages": [
    {
      "min_amount": 1000,
      "max_amount": 1999,
      "effects": [
        {
          "type": "place_fluid",
          "place_fluid": {
            "radius": 0,
            "check_air": true
          },
          "condition": {
            "mod": "create"
          }
        }
      ]
    }
  ]
}
```

### 瀛楁璇存槑

1. **enabled**锛氬竷灏斿€硷紝鏄惁鍚敤姝ら厤缃€?
2. **fluid**锛氬瓧绗︿覆锛屾祦浣撶殑娉ㄥ唽鍚嶏紝鏍煎紡涓?`鍛藉悕绌洪棿:璺緞`銆?
3. **stages**锛氭暟缁勶紝鏁堟灉闃舵鍒楄〃銆?
   - **min_amount**锛氭暣鏁帮紝姝ら樁娈电殑鏈€灏忔祦浣撻噺锛堝崟浣嶏細mb锛夈€?
   - **max_amount**锛氭暣鏁帮紝姝ら樁娈电殑鏈€澶ф祦浣撻噺锛堝崟浣嶏細mb锛夛紝`-1` 琛ㄧず鏃犱笂闄愩€?
   - **effects**锛氭暟缁勶紝姝ら樁娈电殑鏁堟灉鍒楄〃銆?
     - **type**锛氬瓧绗︿覆锛屾晥鏋滅被鍨嬶紝鏀寔浠ヤ笅绫诲瀷锛?
       - `place_fluid`锛氭斁缃祦浣撴簮鏂瑰潡
       - `spawn_items`锛氱敓鎴愮墿鍝佸疄浣?
       - `apply_potion`锛氬鑼冨洿鍐呯敓鐗╂柦鍔犺嵂姘存晥鏋?
       - `explosion`锛氫骇鐢熺垎鐐?
       - `particle`锛氭挱鏀剧矑瀛愭晥鏋?
     - **瀵瑰簲鐨勬晥鏋滈厤缃?*锛氭牴鎹晥鏋滅被鍨嬩笉鍚岋紝閰嶇疆瀛楁涔熶笉鍚屻€?
     - **condition**锛氬彲閫夛紝鏉′欢鍒ゆ柇锛岀洰鍓嶆敮鎸佹娴嬬壒瀹?Mod 鏄惁鍔犺浇銆?

### 鏁堟灉绫诲瀷璇﹁В

#### 1. place_fluid

鏀剧疆娴佷綋婧愭柟鍧椼€?

```json
{
  "type": "place_fluid",
  "place_fluid": {
    "radius": 0,  // 鏀剧疆鍗婂緞锛? 琛ㄧず鍙湪涓績鏀剧疆
    "check_air": true  // 鏄惁鍙湪绌烘皵鏂瑰潡涓婃斁缃?
  }
}
```

#### 2. spawn_items

鐢熸垚鐗╁搧瀹炰綋銆?

```json
{
  "type": "spawn_items",
  "spawn_items": {
    "item": "create:bar_of_chocolate",  // 鐗╁搧鐨勬敞鍐屽悕
    "min_count": 1,  // 鏈€灏忔暟閲?
    "max_count": 9,  // 鏈€澶ф暟閲?
    "chance": 1.0  // 鐢熸垚姒傜巼锛岃寖鍥?0.0-1.0
  }
}
```

#### 3. apply_potion

瀵硅寖鍥村唴鐢熺墿鏂藉姞鑽按鏁堟灉銆?

```json
{
  "type": "apply_potion",
  "apply_potion": {
    "effect": "minecraft:slowness",  // 鑽按鏁堟灉鐨勬敞鍐屽悕
    "duration": 200,  // 鎸佺画鏃堕棿锛堝埢锛?
    "amplifier": 0,  // 鏁堟灉绛夌骇
    "radius": 3  // 褰卞搷鍗婂緞
  }
}
```

#### 4. explosion

浜х敓鐖嗙偢銆?

```json
{
  "type": "explosion",
  "explosion": {
    "power": 1.0,  // 鐖嗙偢濞佸姏
    "break_blocks": false  // 鏄惁鐮村潖鏂瑰潡
  }
}
```

#### 5. particle

鎾斁绮掑瓙鏁堟灉銆?

```json
{
  "type": "particle",
  "particle": {
    "type": "bubble",  // 绮掑瓙绫诲瀷锛屾敮鎸侊細bubble, smoke, effect, witch, bubble_pop
    "count": 20,  // 绮掑瓙鏁伴噺
    "spread": 1.0  // 绮掑瓙鎵╂暎鑼冨洿
  }
}
```

## 蹇€熶笂鎵嬬ず渚?

### 涓哄師鐗堟按娣诲姞鏁堟灉

1. 鍦?`config/fluid_shell_extensions/effects/` 鐩綍涓嬪垱寤?`minecraft_water.json` 鏂囦欢銆?
2. 鍐欏叆浠ヤ笅鍐呭锛?

```json
{
  "enabled": true,
  "fluid": "minecraft:water",
  "stages": [
    {
      "min_amount": 1000,
      "max_amount": 1999,
      "effects": [
        {
          "type": "place_fluid",
          "place_fluid": {
            "radius": 0,
            "check_air": true
          }
        },
        {
          "type": "particle",
          "particle": {
            "type": "bubble",
            "count": 10,
            "spread": 1.0
          }
        }
      ]
    },
    {
      "min_amount": 2000,
      "max_amount": -1,
      "effects": [
        {
          "type": "place_fluid",
          "place_fluid": {
            "radius": 1,
            "check_air": true
          }
        },
        {
          "type": "particle",
          "particle": {
            "type": "bubble",
            "count": 30,
            "spread": 2.0
          }
        }
      ]
    }
  ]
}
```

3. 淇濆瓨鏂囦欢骞堕噸鍚父鎴忋€?
4. 鐜板湪锛屽綋浣犲彂灏勮鏈夋按鐨勬祦浣撶偖寮规椂锛屽畠浼氭牴鎹祦浣撻噺浜х敓涓嶅悓鐨勬晥鏋溿€?

## 楂樼骇鐢ㄦ硶

### 缁勫悎鏁堟灉

浣犲彲浠ュ湪涓€涓樁娈典腑缁勫悎澶氱鏁堟灉锛屼緥濡傦細

```json
{
  "type": "place_fluid",
  "place_fluid": {
    "radius": 1,
    "check_air": true
  }
},
{
  "type": "spawn_items",
  "spawn_items": {
    "item": "minecraft:diamond",
    "min_count": 1,
    "max_count": 3,
    "chance": 0.5
  }
},
{
  "type": "particle",
  "particle": {
    "type": "effect",
    "count": 50,
    "spread": 2.0
  }
}
```

### Mod 鏉′欢妫€娴?

浣犲彲浠ヤ娇鐢?`condition` 瀛楁鏉ユ娴嬬壒瀹?Mod 鏄惁鍔犺浇锛屼緥濡傦細

```json
{
  "type": "place_fluid",
  "place_fluid": {
    "radius": 1,
    "check_air": true
  },
  "condition": {
    "mod": "alexscaves"
  }
}
```

杩欐牱锛屽彧鏈夊綋 Alex's Caves 妯＄粍鍔犺浇鏃讹紝杩欎釜鏁堟灉鎵嶄細鎵ц銆?

## 甯歌闂鎺掓煡

### 閰嶇疆涓嶇敓鏁?

1. 妫€鏌ラ厤缃枃浠剁殑鏍煎紡鏄惁姝ｇ‘锛岀‘淇?JSON 璇硶娌℃湁閿欒銆?
2. 妫€鏌ユ祦浣撶殑娉ㄥ唽鍚嶆槸鍚︽纭紝鏍煎紡涓?`鍛藉悕绌洪棿:璺緞`銆?
3. 妫€鏌ラ厤缃枃浠剁殑鏂囦欢鍚嶆槸鍚︽纭紝寤鸿涓?`鍛藉悕绌洪棿_璺緞.json`銆?
4. 妫€鏌ラ厤缃枃浠舵槸鍚︽斁鍦ㄦ纭殑鐩綍涓嬶細`config/fluid_shell_extensions/effects/`銆?
5. 鏌ョ湅娓告垙鏃ュ織锛屾槸鍚︽湁鍏充簬閰嶇疆鍔犺浇鐨勯敊璇俊鎭€?

### 鏁堟灉鎵ц澶辫触

1. 妫€鏌ラ厤缃腑鐨勭墿鍝併€佹祦浣撴垨鑽按鏁堟灉鐨勬敞鍐屽悕鏄惁姝ｇ‘銆?
2. 妫€鏌ユ槸鍚︽弧瓒虫晥鏋滅殑鏉′欢锛堜緥濡傦紝鏄惁鍔犺浇浜嗙壒瀹氱殑 Mod锛夈€?
3. 鏌ョ湅娓告垙鏃ュ織锛屾槸鍚︽湁鍏充簬鏁堟灉鎵ц鐨勯敊璇俊鎭€?

## 缁撹

娴佷綋寮规晥鏋滄墿灞曚负浣犳彁渚涗簡涓€涓伒娲汇€佸彲鑷畾涔夌殑绯荤粺锛岃浣犺兘澶熶负浠绘剰娴佷綋娣诲姞鐙壒鐨勬晥鏋溿€傞€氳繃鏈暀绋嬶紝浣犲簲璇ュ凡缁忎簡瑙ｄ簡濡備綍浣跨敤 JSON 閰嶇疆鏂囦欢鏉ュ垱寤哄拰淇敼娴佷綋鏁堟灉銆?

濡傛灉浣犳湁浠讳綍闂鎴栧缓璁紝娆㈣繋鍦ㄨ瘎璁哄尯鐣欒█鎴栬仈绯讳綔鑰呫€?

绁濅綘鐜╁緱鎰夊揩锛?
