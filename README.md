# UP Language Support for IntelliJ IDEA

[![CI](https://github.com/uplang/intellij-up/actions/workflows/ci.yml/badge.svg)](https://github.com/uplang/intellij-up/actions/workflows/ci.yml)
[![JetBrains Plugin](https://img.shields.io/jetbrains/plugin/v/com.uplang.intellij-up)](https://plugins.jetbrains.com/plugin/com.uplang.intellij-up)
[![License: GPL v3](https://img.shields.io/badge/License-GPLv3-blue.svg)](https://www.gnu.org/licenses/gpl-3.0)

<!-- Plugin description -->
Official IntelliJ IDEA plugin for [UP (Unified Properties)](https://uplang.org) language support.

Provides comprehensive IDE features including syntax highlighting, code completion, LSP integration, and more.
<!-- Plugin description end -->

**Quick Links:**
- 📦 [Install from Marketplace](https://plugins.jetbrains.com/plugin/com.uplang.intellij-up)
- 📖 [Documentation](#features)
- 🔧 [Development](#development)
- 🐛 [Report Issues](https://github.com/uplang/intellij-up/issues)

## Features

- ✅ **Syntax Highlighting** - Full lexer-based syntax highlighting with customizable colors
- ✅ **Language Server** - Integrated LSP support via LSP4IJ
- ✅ **Code Completion** - Smart completions for keys and namespace functions
- ✅ **Hover Documentation** - Quick documentation on hover
- ✅ **Go to Definition** - Navigate to symbol definitions
- ✅ **Code Formatting** - Format your UP documents
- ✅ **Error Detection** - Real-time syntax and semantic validation
- ✅ **Code Folding** - Fold blocks and lists
- ✅ **Structure View** - Document structure outline
- ✅ **Comment Toggle** - Quick comment/uncomment with `Cmd+/`
- ✅ **Brace Matching** - Automatic brace and bracket matching

## Installation

### From JetBrains Marketplace

1. Open IntelliJ IDEA
2. Go to `Settings` → `Plugins` → `Marketplace`
3. Search for "UP Language Support"
4. Click `Install`
5. Restart IDE

### From Source

```bash
./gradlew buildPlugin
# Install the generated plugin from build/distributions/
```

## Requirements

For full language server features, install the UP language server:

```bash
go install github.com/uplang/tools/language-server@latest
```

The plugin will automatically detect it if it's in your PATH or in `~/go/bin/`.

## Configuration

### Language Server Settings

Go to `Settings` → `Languages & Frameworks` → `Language Servers`

- Enable/disable UP language server
- Configure custom server path
- Set debug options

### Color Scheme

Go to `Settings` → `Editor` → `Color Scheme` → `UP`

Customize colors for:
- Keys and property names
- Type annotations
- Values (strings, numbers)
- Blocks and lists
- Comments
- Multiline strings
- Namespace functions

## Supported File Types

- `.up` - UP configuration files
- `.up-schema` - UP schema definition files

## Keyboard Shortcuts

| Action | Shortcut (Mac) | Shortcut (Win/Linux) |
|--------|----------------|----------------------|
| Toggle Comment | `Cmd+/` | `Ctrl+/` |
| Format Document | `Cmd+Alt+L` | `Ctrl+Alt+L` |
| Go to Definition | `Cmd+B` | `Ctrl+B` |
| Show Documentation | `F1` | `Ctrl+Q` |
| Code Completion | `Ctrl+Space` | `Ctrl+Space` |

## Troubleshooting

### Language Server Not Starting

1. Check if `up-language-server` is installed:
   ```bash
   which up-language-server
   ```

2. Install if missing:
   ```bash
   go install github.com/uplang/tools/language-server@latest
   ```

3. Check IDE logs: `Help` → `Show Log in Finder/Explorer`

4. Restart language server: `Tools` → `Restart UP Language Server`

### Syntax Highlighting Not Working

1. Ensure file has `.up` extension
2. Check if UP language is registered: `Settings` → `Editor` → `File Types`
3. Invalidate caches: `File` → `Invalidate Caches / Restart`

## Development

### Building

```bash
# Build plugin
./gradlew buildPlugin

# Run IDE with plugin
./gradlew runIde

# Run tests
./gradlew test

# Check plugin
./gradlew verifyPlugin
```

### Project Structure

```
intellij-up/
├── build.gradle.kts              # Build configuration
├── src/main/
│   ├── kotlin/org/uplang/intellij/
│   │   ├── UpLanguage.kt         # Language definition
│   │   ├── UpFileType.kt         # File type
│   │   ├── UpCommenter.kt        # Comment support
│   │   ├── UpBraceMatcher.kt     # Brace matching
│   │   ├── highlighting/         # Syntax highlighter
│   │   ├── lsp/                  # LSP integration
│   │   ├── parser/               # Parser definition
│   │   └── folding/              # Code folding
│   └── resources/
│       ├── META-INF/plugin.xml   # Plugin manifest
│       └── icons/                # Icons
└── README.md
```

## Contributing

Contributions are welcome! See [CONTRIBUTING.md](https://github.com/uplang/spec/blob/main/CONTRIBUTING.md) for guidelines.

## License

GNU General Public License v3.0 - see [LICENSE](LICENSE) for details.

## Links

- [UP Language Specification](https://github.com/uplang/spec)
- [UP Language Server](https://github.com/uplang/tools/tree/main/language-server)
- [UP Tools](https://github.com/uplang/tools)
- [IntelliJ Platform SDK](https://plugins.jetbrains.com/docs/intellij/)
- [LSP4IJ](https://github.com/redhat-developer/lsp4ij)

## Release Notes

### 0.1.0 (Initial Release)

- Syntax highlighting with customizable color schemes
- Language Server Protocol integration via LSP4IJ
- Code completion and hover documentation
- Go to definition and find usages
- Document formatting and validation
- Code folding and structure view
- Comment toggling and brace matching
- File icons and editor support

