package io.swagger.codegen.languages;

import io.swagger.codegen.CliOption;
import io.swagger.codegen.SupportingFile;

import java.io.File;

public class TypeScriptFetchClientCodegen extends AbstractTypeScriptClientCodegen {

    public static final String NPM_NAME = "npmName";
    public static final String NPM_VERSION = "npmVersion";

    protected String npmName = null;
    protected String npmVersion = "1.0.0";

    public TypeScriptFetchClientCodegen() {
        super();

        // clear import mapping (from default generator) as TS does not use it
        // at the moment
        importMapping.clear();

        outputFolder = "generated-code/typescript-fetch";
        embeddedTemplateDir = templateDir = "TypeScript-Fetch";
        this.cliOptions.add(new CliOption(NPM_NAME, "The name under which you want to publish generated npm package"));
        this.cliOptions.add(new CliOption(NPM_VERSION, "The version of your npm package"));
    }

    @Override
    public void processOpts() {
        super.processOpts();
        supportingFiles.add(new SupportingFile("api.mustache", "", "api.ts"));
        supportingFiles.add(new SupportingFile("git_push.sh.mustache", "", "git_push.sh"));
        supportingFiles.add(new SupportingFile("README.md", "", "README.md"));
        supportingFiles.add(new SupportingFile("package.json.mustache", "", "package.json"));
        supportingFiles.add(new SupportingFile("typings.json.mustache", "", "typings.json"));
        supportingFiles.add(new SupportingFile("tsconfig.json.mustache", "", "tsconfig.json"));
        supportingFiles.add(new SupportingFile("gitignore", "", ".gitignore"));

        if(additionalProperties.containsKey(NPM_NAME)) {
            this.setNpmName(additionalProperties.get(NPM_NAME).toString());
        }

        if (additionalProperties.containsKey(NPM_VERSION)) {
            this.setNpmVersion(additionalProperties.get(NPM_VERSION).toString());
        }
    }

    @Override
    public String getName() {
        return "typescript-fetch";
    }

    @Override
    public String getHelp() {
        return "Generates a TypeScript client library using Fetch API (beta).";
    }

    public String getNpmName() {
        return npmName;
    }

    public void setNpmName(String npmName) {
        this.npmName = npmName;
    }

    public String getNpmVersion() {
        return npmVersion;
    }

    public void setNpmVersion(String npmVersion) {
        this.npmVersion = npmVersion;
    }

}
