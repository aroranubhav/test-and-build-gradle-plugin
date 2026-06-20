// ============================================================
// Root build.gradle.kts
//
// Deliberately empty. This project's root is not a "module" that
// compiles into anything — it's just the top of the tree. The actual compiled,
// publishable artifact is the :testandbuild module's job.
//
// If we ever add a second subproject (e.g. a future sibling plugin),
// shared configuration across subprojects could live here via
// subprojects {} or allprojects {}. For now, with exactly one
// subproject, there's nothing to share — so this file just exists
// to mark the root as a recognized Gradle project.
// ============================================================