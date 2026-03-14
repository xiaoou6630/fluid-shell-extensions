**好，直接全英文，简洁专业。**

---

## ✅ 全英文 README（复制替换）

```markdown
# Fluid Shell Extensions

![Cover](https://cdn.modrinth.com/data/cached_images/cdf4784d983df2f4271707cf9b539e1c23374299.png)

## Overview

**Fluid Shell Extensions** is an addon mod for [Create Big Cannons] that expands the fluid shell system with a flexible custom effect framework. Through simple JSON configuration files, you can add rich explosive effects to any fluid without writing a single line of code.

## Features

- **Fully Data-Driven**: All effects are configured via JSON files.
- **Multi-Stage Effects**: Trigger different effects based on fluid amount inside the shell.
- **Multiple Effect Types**:
  - Place fluid source blocks
  - Spawn item entities
  - Apply potion effects to entities within range
  - Create explosions
  - Emit particles
- **Soft Dependency Support**: Set conditions for specific mods.
- **Detailed Logging**: Easy debugging and effect tracing.

## Requirements

- Minecraft 1.20.1
- Forge 47.0.0+
- [Create] 6.0.0+
- [Create Big Cannons] 5.10.2+

## Configuration

Place your JSON extension files in:
```
config/fluid_shell_extensions/effects/
```

For detailed writing guide, see [EXTENDING.md](EXTENDING.md).

## Open Source

This mod is open source under the MIT License.

## FAQ

**Q: How do I create custom extensions?**  
A: See [EXTENDING.md](EXTENDING.md).

**Q: How to install on a server?**  
A: Place config files in `serverconfig/fluid_shell_extensions/effects/` (server-side takes priority).

**Q: I want to report a bug or suggest a feature?**  
A: Leave a comment on Modrinth or open an issue on GitHub.

## License

MIT License © 2026 xiaoou6630
```

