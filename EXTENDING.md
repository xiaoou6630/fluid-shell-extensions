# 流体弹效果扩展 - 开发教程

## 简介

流体弹效果扩展（Fluid Shell Extensions）是一个为 Minecraft 1.20.1 Forge 的 Create Big Cannons 模组开发的附属 Mod，它允许你通过 JSON 配置文件来自定义任意流体的效果，而无需修改代码。

本教程将帮助你了解如何使用 JSON 配置文件来添加和自定义流体效果，让你的流体炮弹更加多样化和有趣。

## 配置文件位置

配置文件位于 `config/fluid_shell_extensions/effects/` 目录下，每个流体一个 `.json` 文件。文件名建议为 `命名空间_路径.json`，例如 `create_chocolate.json`。

## JSON 格式详解

### 基本结构

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

### 字段说明

1. **enabled**：布尔值，是否启用此配置。
2. **fluid**：字符串，流体的注册名，格式为 `命名空间:路径`。
3. **stages**：数组，效果阶段列表。
   - **min_amount**：整数，此阶段的最小流体量（单位：mb）。
   - **max_amount**：整数，此阶段的最大流体量（单位：mb），`-1` 表示无上限。
   - **effects**：数组，此阶段的效果列表。
     - **type**：字符串，效果类型，支持以下类型：
       - `place_fluid`：放置流体源方块
       - `spawn_items`：生成物品实体
       - `apply_potion`：对范围内生物施加药水效果
       - `explosion`：产生爆炸
       - `particle`：播放粒子效果
     - **对应的效果配置**：根据效果类型不同，配置字段也不同。
     - **condition**：可选，条件判断，目前支持检测特定 Mod 是否加载。

### 效果类型详解

#### 1. place_fluid

放置流体源方块。

```json
{
  "type": "place_fluid",
  "place_fluid": {
    "radius": 0,  // 放置半径，0 表示只在中心放置
    "check_air": true  // 是否只在空气方块上放置
  }
}
```

#### 2. spawn_items

生成物品实体。

```json
{
  "type": "spawn_items",
  "spawn_items": {
    "item": "create:bar_of_chocolate",  // 物品的注册名
    "min_count": 1,  // 最小数量
    "max_count": 9,  // 最大数量
    "chance": 1.0  // 生成概率，范围 0.0-1.0
  }
}
```

#### 3. apply_potion

对范围内生物施加药水效果。

```json
{
  "type": "apply_potion",
  "apply_potion": {
    "effect": "minecraft:slowness",  // 药水效果的注册名
    "duration": 200,  // 持续时间（刻）
    "amplifier": 0,  // 效果等级
    "radius": 3  // 影响半径
  }
}
```

#### 4. explosion

产生爆炸。

```json
{
  "type": "explosion",
  "explosion": {
    "power": 1.0,  // 爆炸威力
    "break_blocks": false  // 是否破坏方块
  }
}
```

#### 5. particle

播放粒子效果。

```json
{
  "type": "particle",
  "particle": {
    "type": "bubble",  // 粒子类型，支持：bubble, smoke, effect, witch, bubble_pop
    "count": 20,  // 粒子数量
    "spread": 1.0  // 粒子扩散范围
  }
}
```

## 快速上手示例

### 为原版水添加效果

1. 在 `config/fluid_shell_extensions/effects/` 目录下创建 `minecraft_water.json` 文件。
2. 写入以下内容：

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

3. 保存文件并重启游戏。
4. 现在，当你发射装有水的流体炮弹时，它会根据流体量产生不同的效果。

## 高级用法

### 组合效果

你可以在一个阶段中组合多种效果，例如：

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

### Mod 条件检测

你可以使用 `condition` 字段来检测特定 Mod 是否加载，例如：

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

这样，只有当 Alex's Caves 模组加载时，这个效果才会执行。

## 常见问题排查

### 配置不生效

1. 检查配置文件的格式是否正确，确保 JSON 语法没有错误。
2. 检查流体的注册名是否正确，格式为 `命名空间:路径`。
3. 检查配置文件的文件名是否正确，建议为 `命名空间_路径.json`。
4. 检查配置文件是否放在正确的目录下：`config/fluid_shell_extensions/effects/`。
5. 查看游戏日志，是否有关于配置加载的错误信息。

### 效果执行失败

1. 检查配置中的物品、流体或药水效果的注册名是否正确。
2. 检查是否满足效果的条件（例如，是否加载了特定的 Mod）。
3. 查看游戏日志，是否有关于效果执行的错误信息。

## 结语

流体弹效果扩展为你提供了一个灵活、可自定义的系统，让你能够为任意流体添加独特的效果。通过本教程，你应该已经了解了如何使用 JSON 配置文件来创建和修改流体效果。

如果你有任何问题或建议，欢迎在评论区留言或联系作者。

祝你玩得愉快！
