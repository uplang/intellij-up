# Makefile for UP IntelliJ Plugin
.PHONY: help build run test clean package publish

help: ## Show this help message
	@echo 'Usage: make [target]'
	@echo ''
	@echo 'Available targets:'
	@awk 'BEGIN {FS = ":.*?## "} /^[a-zA-Z_-]+:.*?## / {printf "  %-15s %s\n", $$1, $$2}' $(MAKEFILE_LIST)

build: ## Build the plugin
	./gradlew buildPlugin

run: ## Run IDE with plugin
	./gradlew runIde

test: ## Run tests
	./gradlew test

verify: ## Verify plugin
	./gradlew verifyPlugin

clean: ## Clean build artifacts
	./gradlew clean

package: build ## Package plugin for distribution
	@echo "Plugin packaged in build/distributions/"

publish: ## Publish to JetBrains Marketplace
	./gradlew publishPlugin

install: ## Install Gradle wrapper (if needed)
	gradle wrapper --gradle-version 8.5

.DEFAULT_GOAL := help

