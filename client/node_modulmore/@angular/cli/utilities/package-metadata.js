"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
const fs_1 = require("fs");
const os_1 = require("os");
const path = require("path");
const ini = require('ini');
const lockfile = require('@yarnpkg/lockfile');
const pacote = require('pacote');
let npmrc;
function ensureNpmrc(logger, usingYarn, verbose) {
    if (!npmrc) {
        try {
            npmrc = readOptions(logger, false, verbose);
        }
        catch (_a) { }
        if (usingYarn) {
            try {
                npmrc = Object.assign({}, npmrc, readOptions(logger, true, verbose));
            }
            catch (_b) { }
        }
    }
}
function readOptions(logger, yarn = false, showPotentials = false) {
    const cwd = process.cwd();
    const baseFilename = yarn ? 'yarnrc' : 'npmrc';
    const dotFilename = '.' + baseFilename;
    let globalPrefix;
    if (process.env.PREFIX) {
        globalPrefix = process.env.PREFIX;
    }
    else {
        globalPrefix = path.dirname(process.execPath);
        if (process.platform !== 'win32') {
            globalPrefix = path.dirname(globalPrefix);
        }
    }
    const defaultConfigLocations = [
        path.join(globalPrefix, 'etc', baseFilename),
        path.join(os_1.homedir(), dotFilename),
    ];
    const projectConfigLocations = [
        path.join(cwd, dotFilename),
    ];
    const root = path.parse(cwd).root;
    for (let curDir = path.dirname(cwd); curDir && curDir !== root; curDir = path.dirname(curDir)) {
        projectConfigLocations.unshift(path.join(curDir, dotFilename));
    }
    if (showPotentials) {
        logger.info(`Locating potential ${baseFilename} files:`);
    }
    let options = {};
    for (const location of [...defaultConfigLocations, ...projectConfigLocations]) {
        if (fs_1.existsSync(location)) {
            if (showPotentials) {
                logger.info(`Trying '${location}'...found.`);
            }
            const data = fs_1.readFileSync(location, 'utf8');
            options = Object.assign({}, options, (yarn ? lockfile.parse(data) : ini.parse(data)));
            if (options.cafile) {
                const cafile = path.resolve(path.dirname(location), options.cafile);
                delete options.cafile;
                try {
                    options.ca = fs_1.readFileSync(cafile, 'utf8').replace(/\r?\n/, '\\n');
                }
                catch (_a) { }
            }
        }
        else if (showPotentials) {
            logger.info(`Trying '${location}'...not found.`);
        }
    }
    // Substitute any environment variable references
    for (const key in options) {
        if (typeof options[key] === 'string') {
            options[key] = options[key].replace(/\$\{([^\}]+)\}/, (_, name) => process.env[name] || '');
        }
    }
    return options;
}
function normalizeManifest(rawManifest) {
    // TODO: Fully normalize and sanitize
    return Object.assign({ dependencies: {}, devDependencies: {}, peerDependencies: {}, optionalDependencies: {} }, rawManifest);
}
async function fetchPackageMetadata(name, logger, options) {
    const { usingYarn, verbose, registry } = Object.assign({ registry: undefined, usingYarn: false, verbose: false }, options);
    ensureNpmrc(logger, usingYarn, verbose);
    const response = await pacote.packument(name, Object.assign({ 'full-metadata': true }, npmrc, (registry ? { registry } : {})));
    // Normalize the response
    const metadata = {
        name: response.name,
        tags: {},
        versions: new Map(),
    };
    if (response.versions) {
        for (const [version, manifest] of Object.entries(response.versions)) {
            metadata.versions.set(version, normalizeManifest(manifest));
        }
    }
    if (response['dist-tags']) {
        for (const [tag, version] of Object.entries(response['dist-tags'])) {
            const manifest = metadata.versions.get(version);
            if (manifest) {
                metadata.tags[tag] = manifest;
            }
            else if (verbose) {
                logger.warn(`Package ${metadata.name} has invalid version metadata for '${tag}'.`);
            }
        }
    }
    return metadata;
}
exports.fetchPackageMetadata = fetchPackageMetadata;
async function fetchPackageManifest(name, logger, options) {
    const { usingYarn, verbose, registry } = Object.assign({ registry: undefined, usingYarn: false, verbose: false }, options);
    ensureNpmrc(logger, usingYarn, verbose);
    const response = await pacote.manifest(name, Object.assign({ 'full-metadata': true }, npmrc, (registry ? { registry } : {})));
    return normalizeManifest(response);
}
exports.fetchPackageManifest = fetchPackageManifest;
//# sourceMappingURL=data:application/json;base64,eyJ2ZXJzaW9uIjozLCJmaWxlIjoicGFja2FnZS1tZXRhZGF0YS5qcyIsInNvdXJjZVJvb3QiOiIuLyIsInNvdXJjZXMiOlsicGFja2FnZXMvYW5ndWxhci9jbGkvdXRpbGl0aWVzL3BhY2thZ2UtbWV0YWRhdGEudHMiXSwibmFtZXMiOltdLCJtYXBwaW5ncyI6Ijs7QUFRQSwyQkFBOEM7QUFDOUMsMkJBQTZCO0FBQzdCLDZCQUE2QjtBQUU3QixNQUFNLEdBQUcsR0FBRyxPQUFPLENBQUMsS0FBSyxDQUFDLENBQUM7QUFDM0IsTUFBTSxRQUFRLEdBQUcsT0FBTyxDQUFDLG1CQUFtQixDQUFDLENBQUM7QUFDOUMsTUFBTSxNQUFNLEdBQUcsT0FBTyxDQUFDLFFBQVEsQ0FBQyxDQUFDO0FBeUNqQyxJQUFJLEtBQWdDLENBQUM7QUFFckMsU0FBUyxXQUFXLENBQUMsTUFBeUIsRUFBRSxTQUFrQixFQUFFLE9BQWdCO0lBQ2xGLElBQUksQ0FBQyxLQUFLLEVBQUU7UUFDVixJQUFJO1lBQ0YsS0FBSyxHQUFHLFdBQVcsQ0FBQyxNQUFNLEVBQUUsS0FBSyxFQUFFLE9BQU8sQ0FBQyxDQUFDO1NBQzdDO1FBQUMsV0FBTSxHQUFHO1FBRVgsSUFBSSxTQUFTLEVBQUU7WUFDYixJQUFJO2dCQUNGLEtBQUsscUJBQVEsS0FBSyxFQUFLLFdBQVcsQ0FBQyxNQUFNLEVBQUUsSUFBSSxFQUFFLE9BQU8sQ0FBQyxDQUFFLENBQUM7YUFDN0Q7WUFBQyxXQUFNLEdBQUc7U0FDWjtLQUNGO0FBQ0gsQ0FBQztBQUVELFNBQVMsV0FBVyxDQUNsQixNQUF5QixFQUN6QixJQUFJLEdBQUcsS0FBSyxFQUNaLGNBQWMsR0FBRyxLQUFLO0lBRXRCLE1BQU0sR0FBRyxHQUFHLE9BQU8sQ0FBQyxHQUFHLEVBQUUsQ0FBQztJQUMxQixNQUFNLFlBQVksR0FBRyxJQUFJLENBQUMsQ0FBQyxDQUFDLFFBQVEsQ0FBQyxDQUFDLENBQUMsT0FBTyxDQUFDO0lBQy9DLE1BQU0sV0FBVyxHQUFHLEdBQUcsR0FBRyxZQUFZLENBQUM7SUFFdkMsSUFB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SSxJQUNsQixLQUFLLEVBQ0wsQ0FBQyxRQUFRLENBQUMsQ0FBQyxDQUFDLEVBQUUsUUFBUSxFQUFFLENBQUMsQ0FBQyxDQUFDLEVBQUUsQ0FBQyxFQUVwQyxDQUFDO0lBRUYsT0FBTyxpQkFBaUIsQ0FBQyxRQUFRLENBQUMsQ0FBQztBQUNyQyxDQUFDO0FBNUJELG9EQTRCQyIsInNvdXJjZXNDb250ZW50IjpbIi8qKlxuICogQGxpY2Vuc2VcbiAqIENvcHlyaWdodCBHb29nbGUgSW5jLiBBbGwgUmlnaHRzIFJlc2VydmVkLlxuICpcbiAqIFVzZSBvZiB0aGlzIHNvdXJjZSBjb2RlIGlzIGdvdmVybmVkIGJ5IGFuIE1JVC1zdHlsZSBsaWNlbnNlIHRoYXQgY2FuIGJlXG4gKiBmb3VuZCBpbiB0aGUgTElDRU5TRSBmaWxlIGF0IGh0dHBzOi8vYW5ndWxhci5pby9saWNlbnNlXG4gKi9cbmltcG9ydCB7IGxvZ2dpbmcgfSBmcm9tICdAYW5ndWxhci1kZXZraXQvY29yZSc7XG5pbXBvcnQgeyBleGlzdHNTeW5jLCByZWFkRmlsZVN5bmMgfSBmcm9tICdmcyc7XG5pbXBvcnQgeyBob21lZGlyIH0gZnJvbSAnb3MnO1xuaW1wb3J0ICogYXMgcGF0aCBmcm9tICdwYXRoJztcblxuY29uc3QgaW5pID0gcmVxdWlyZSgnaW5pJyk7XG5jb25zdCBsb2NrZmlsZSA9IHJlcXVpcmUoJ0B5YXJucGtnL2xvY2tmaWxlJyk7XG5jb25zdCBwYWNvdGUgPSByZXF1aXJlKCdwYWNvdGUnKTtcblxuZXhwb3J0IGludGVyZmFjZSBQYWNrYWdlRGVwZW5kZW5jaWVzIHtcbiAgW2RlcGVuZGVuY3k6IHN0cmluZ106IHN0cmluZztcbn1cblxuZXhwb3J0IGludGVyZmFjZSBQYWNrYWdlSWRlbnRpZmllciB7XG4gIHR5cGU6ICdnaXQnIHwgJ3RhZycgfCAndmVyc2lvbicgfCAncmFuZ2UnIHwgJ2ZpbGUnIHwgJ2RpcmVjdG9yeScgfCAncmVtb3RlJztcbiAgbmFtZTogc3RyaW5nO1xuICBzY29wZTogc3RyaW5nIHwgbnVsbDtcbiAgcmVnaXN0cnk6IGJvb2xlYW47XG4gIHJhdzogc3RyaW5nO1xufVxuXG5leHBvcnQgaW50ZXJmYWNlIFBhY2thZ2VNYW5pZmVzdCB7XG4gIG5hbWU6IHN0cmluZztcbiAgdmVyc2lvbjogc3RyaW5nO1xuICBsaWNlbnNlPzogc3RyaW5nO1xuICBwcml2YXRlPzogYm9vbGVhbjtcbiAgZGVwcmVjYXRlZD86IGJvb2xlYW47XG5cbiAgZGVwZW5kZW5jaWVzOiBQYWNrYWdlRGVwZW5kZW5jaWVzO1xuICBkZXZEZXBlbmRlbmNpZXM6IFBhY2thZ2VEZXBlbmRlbmNpZXM7XG4gIHBlZXJEZXBlbmRlbmNpZXM6IFBhY2thZ2VEZXBlbmRlbmNpZXM7XG4gIG9wdGlvbmFsRGVwZW5kZW5jaWVzOiBQYWNrYWdlRGVwZW5kZW5jaWVzO1xuXG4gICduZy1hZGQnPzoge1xuXG4gIH07XG4gICduZy11cGRhdGUnPzoge1xuICAgIG1pZ3JhdGlvbnM6IHN0cmluZyxcbiAgICBwYWNrYWdlR3JvdXA6IHsgW25hbWU6IHN0cmluZ106IHN0cmluZyB9LFxuICB9O1xufVxuXG5leHBvcnQgaW50ZXJmYWNlIFBhY2thZ2VNZXRhZGF0YSB7XG4gIG5hbWU6IHN0cmluZztcbiAgdGFnczogeyBbdGFnOiBzdHJpbmddOiBQYWNrYWdlTWFuaWZlc3QgfCB1bmRlZmluZWQgfTtcbiAgdmVyc2lvbnM6IE1hcDxzdHJpbmcsIFBhY2thZ2VNYW5pZmVzdD47XG59XG5cbmxldCBucG1yYzogeyBba2V5OiBzdHJpbmddOiBzdHJpbmcgfTtcblxuZnVuY3Rpb24gZW5zdXJlTnBtcmMobG9nZ2VyOiBsb2dnaW5nLkxvZ2dlckFwaSwgdXNpbmdZYXJuOiBib29sZWFuLCB2ZXJib3NlOiBib29sZWFuKTogdm9pZCB7XG4gIGlmICghbnBtcmMpIHtcbiAgICB0cnkge1xuICAgICAgbnBtcmMgPSByZWFkT3B0aW9ucyhsb2dnZXIsIGZhbHNlLCB2ZXJib3NlKTtcbiAgICB9IGNhdGNoIHsgfVxuXG4gICAgaWYgKHVzaW5nWWFybikge1xuICAgICAgdHJ5IHtcbiAgICAgICAgbnBtcmMgPSB7IC4uLm5wbXJjLCAuLi5yZWFkT3B0aW9ucyhsb2dnZXIsIHRydWUsIHZlcmJvc2UpIH07XG4gICAgICB9IGNhdGNoIHsgfVxuICAgIH1cbiAgfVxufVxuXG5mdW5jdGlvbiByZWFkT3B0aW9ucyhcbiAgbG9nZ2VyOiBsb2dnaW5nLkxvZ2dlckFwaSxcbiAgeWFybiA9IGZhbHNlLFxuICBzaG93UG90ZW50aWFscyA9IGZhbHNlLFxuKTogUmVjb3JkPHN0cmluZywgc3RyaW5nPiB7XG4gIGNvbnN0IGN3ZCA9IHByb2Nlc3MuY3dkKCk7XG4gIGNvbnN0IGJhc2VGaWxlbmFtZSA9IHlhcm4gPyAneWFybnJjJyA6ICducG1yYyc7XG4gIGNvbnN0IGRvdEZpbGVuYW1lID0gJy4nICsgYmFzZUZpbGVuYW1lO1xuXG4gIGxldCBnbG9iYWxQcmVmaXg6IHN0cmluZztcbiAgaWYgKHByb2Nlc3MuZW52LlBSRUZJWCkge1xuICAgIGdsb2JhbFByZWZpeCA9IHByb2Nlc3MuZW52LlBSRUZJWDtcbiAgfSBlbHNlIHtcbiAgICBnbG9iYWxQcmVmaXggPSBwYXRoLmRpcm5hbWUocHJvY2Vzcy5leGVjUGF0aCk7XG4gICAgaWYgKHByb2Nlc3MucGxhdGZvcm0gIT09ICd3aW4zMicpIHtcbiAgICAgIGdsb2JhbFByZWZpeCA9IHBhdGguZGlybmFtZShnbG9iYWxQcmVmaXgpO1xuICAgIH1cbiAgfVxuXG4gIGNvbnN0IGRlZmF1bHRDb25maWdMb2NhdGlvbnMgPSBbXG4gICAgcGF0aC5qb2luKGdsb2JhbFByZWZpeCwgJ2V0YycsIGJhc2VGaWxlbmFtZSksXG4gICAgcGF0aC5qb2luKGhvbWVkaXIoKSwgZG90RmlsZW5hbWUpLFxuICBdO1xuXG4gIGNvbnN0IHByb2plY3RDb25maWdMb2NhdGlvbnM6IHN0cmluZ1tdID0gW1xuICAgIHBhdGguam9pbihjd2QsIGRvdEZpbGVuYW1lKSxcbiAgXTtcbiAgY29uc3Qgcm9vdCA9IHBhdGgucGFyc2UoY3dkKS5yb290O1xuICBmb3IgKGxldCBjdXJEaXIgPSBwYXRoLmRpcm5hbWUoY3dkKTsgY3VyRGlyICYmIGN1ckRpciAhPT0gcm9vdDsgY3VyRGlyID0gcGF0aC5kaXJuYW1lKGN1ckRpcikpIHtcbiAgICBwcm9qZWN0Q29uZmlnTG9jYXRpb25zLnVuc2hpZnQocGF0aC5qb2luKGN1ckRpciwgZG90RmlsZW5hbWUpKTtcbiAgfVxuXG4gIGlmIChzaG93UG90ZW50aWFscykge1xuICAgIGxvZ2dlci5pbmZvKGBMb2NhdGluZyBwb3RlbnRpYWwgJHtiYXNlRmlsZW5hbWV9IGZpbGVzOmApO1xuICB9XG5cbiAgbGV0IG9wdGlvbnM6IHsgW2tleTogc3RyaW5nXTogc3RyaW5nIH0gPSB7fTtcbiAgZm9yIChjb25zdCBsb2NhdGlvbiBvZiBbLi4uZGVmYXVsdENvbmZpZ0xvY2F0aW9ucywgLi4ucHJvamVjdENvbmZpZ0xvY2F0aW9uc10pIHtcbiAgICBpZiAoZXhpc3RzU3luYyhsb2NhdGlvbikpIHtcbiAgICAgIGlmIChzaG93UG90ZW50aWFscykge1xuICAgICAgICBsb2dnZXIuaW5mbyhgVHJ5aW5nICcke2xvY2F0aW9ufScuLi5mb3VuZC5gKTtcbiAgICAgIH1cblxuICAgICAgY29uc3QgZGF0YSA9IHJlYWRGaWxlU3luYyhsb2NhdGlvbiwgJ3V0ZjgnKTtcbiAgICAgIG9wdGlvbnMgPSB7XG4gICAgICAgIC4uLm9wdGlvbnMsXG4gICAgICAgIC4uLih5YXJuID8gbG9ja2ZpbGUucGFyc2UoZGF0YSkgOiBpbmkucGFyc2UoZGF0YSkpLFxuICAgICAgfTtcblxuICAgICAgaWYgKG9wdGlvbnMuY2FmaWxlKSB7XG4gICAgICAgIGNvbnN0IGNhZmlsZSA9IHBhdGgucmVzb2x2ZShwYXRoLmRpcm5hbWUobG9jYXRpb24pLCBvcHRpb25zLmNhZmlsZSk7XG4gICAgICAgIGRlbGV0ZSBvcHRpb25zLmNhZmlsZTtcbiAgICAgICAgdHJ5IHtcbiAgICAgICAgICBvcHRpb25zLmNhID0gcmVhZEZpbGVTeW5jKGNhZmlsZSwgJ3V0ZjgnKS5yZXBsYWNlKC9cXHI/XFxuLywgJ1xcXFxuJyk7XG4gICAgICAgIH0gY2F0Y2ggeyB9XG4gICAgICB9XG4gICAgfSBlbHNlIGlmIChzaG93UG90ZW50aWFscykge1xuICAgICAgbG9nZ2VyLmluZm8oYFRyeWluZyAnJHtsb2NhdGlvbn0nLi4ubm90IGZvdW5kLmApO1xuICAgIH1cbiAgfVxuXG4gIC8vIFN1YnN0aXR1dGUgYW55IGVudmlyb25tZW50IHZhcmlhYmxlIHJlZmVyZW5jZXNcbiAgZm9yIChjb25zdCBrZXkgaW4gb3B0aW9ucykge1xuICAgIGlmICh0eXBlb2Ygb3B0aW9uc1trZXldID09PSAnc3RyaW5nJykge1xuICAgICAgb3B0aW9uc1trZXldID0gb3B0aW9uc1trZXldLnJlcGxhY2UoL1xcJFxceyhbXlxcfV0rKVxcfS8sIChfLCBuYW1lKSA9PiBwcm9jZXNzLmVudltuYW1lXSB8fCAnJyk7XG4gICAgfVxuICB9XG5cbiAgcmV0dXJuIG9wdGlvbnM7XG59XG5cbmZ1bmN0aW9uIG5vcm1hbGl6ZU1hbmlmZXN0KHJhd01hbmlmZXN0OiB7fSk6IFBhY2thZ2VNYW5pZmVzdCB7XG4gIC8vIFRPRE86IEZ1bGx5IG5vcm1hbGl6ZSBhbmQgc2FuaXRpemVcblxuICByZXR1cm4ge1xuICAgIGRlcGVuZGVuY2llczoge30sXG4gICAgZGV2RGVwZW5kZW5jaWVzOiB7fSxcbiAgICBwZWVyRGVwZW5kZW5jaWVzOiB7fSxcbiAgICBvcHRpb25hbERlcGVuZGVuY2llczoge30sXG4gICAgLy8gdHNsaW50OmRpc2FibGUtbmV4dC1saW5lOm5vLWFueVxuICAgIC4uLnJhd01hbmlmZXN0IGFzIGFueSxcbiAgfTtcbn1cblxuZXhwb3J0IGFzeW5jIGZ1bmN0aW9uIGZldGNoUGFja2FnZU1ldGFkYXRhKFxuICBuYW1lOiBzdHJpbmcsXG4gIGxvZ2dlcjogbG9nZ2luZy5Mb2dnZXJBcGksXG4gIG9wdGlvbnM/OiB7XG4gICAgcmVnaXN0cnk/OiBzdHJpbmc7XG4gICAgdXNpbmdZYXJuPzogYm9vbGVhbjtcbiAgICB2ZXJib3NlPzogYm9vbGVhbjtcbiAgfSxcbik6IFByb21pc2U8UGFja2FnZU1ldGFkYXRhPiB7XG4gIGNvbnN0IHsgdXNpbmdZYXJuLCB2ZXJib3NlLCByZWdpc3RyeSB9ID0ge1xuICAgIHJlZ2lzdHJ5OiB1bmRlZmluZWQsXG4gICAgdXNpbmdZYXJuOiBmYWxzZSxcbiAgICB2ZXJib3NlOiBmYWxzZSxcbiAgICAuLi5vcHRpb25zLFxuICB9O1xuXG4gIGVuc3VyZU5wbXJjKGxvZ2dlciwgdXNpbmdZYXJuLCB2ZXJib3NlKTtcblxuICBjb25zdCByZXNwb25zZSA9IGF3YWl0IHBhY290ZS5wYWNrdW1lbnQoXG4gICAgbmFtZSxcbiAgICB7XG4gICAgICAnZnVsbC1tZXRhZGF0YSc6IHRydWUsXG4gICAgICAuLi5ucG1yYyxcbiAgICAgIC4uLihyZWdpc3RyeSA/IHsgcmVnaXN0cnkgfSA6IHt9KSxcbiAgICB9LFxuICApO1xuXG4gIC8vIE5vcm1hbGl6ZSB0aGUgcmVzcG9uc2VcbiAgY29uc3QgbWV0YWRhdGE6IFBhY2thZ2VNZXRhZGF0YSA9IHtcbiAgICBuYW1lOiByZXNwb25zZS5uYW1lLFxuICAgIHRhZ3M6IHt9LFxuICAgIHZlcnNpb25zOiBuZXcgTWFwKCksXG4gIH07XG5cbiAgaWYgKHJlc3BvbnNlLnZlcnNpb25zKSB7XG4gICAgZm9yIChjb25zdCBbdmVyc2lvbiwgbWFuaWZlc3RdIG9mIE9iamVjdC5lbnRyaWVzKHJlc3BvbnNlLnZlcnNpb25zKSkge1xuICAgICAgbWV0YWRhdGEudmVyc2lvbnMuc2V0KHZlcnNpb24sIG5vcm1hbGl6ZU1hbmlmZXN0KG1hbmlmZXN0KSk7XG4gICAgfVxuICB9XG5cbiAgaWYgKHJlc3BvbnNlWydkaXN0LXRhZ3MnXSkge1xuICAgIGZvciAoY29uc3QgW3RhZywgdmVyc2lvbl0gb2YgT2JqZWN0LmVudHJpZXMocmVzcG9uc2VbJ2Rpc3QtdGFncyddKSkge1xuICAgICAgY29uc3QgbWFuaWZlc3QgPSBtZXRhZGF0YS52ZXJzaW9ucy5nZXQodmVyc2lvbiBhcyBzdHJpbmcpO1xuICAgICAgaWYgKG1hbmlmZXN0KSB7XG4gICAgICAgIG1ldGFkYXRhLnRhZ3NbdGFnXSA9IG1hbmlmZXN0O1xuICAgICAgfSBlbHNlIGlmICh2ZXJib3NlKSB7XG4gICAgICAgIGxvZ2dlci53YXJuKGBQYWNrYWdlICR7bWV0YWRhdGEubmFtZX0gaGFzIGludmFsaWQgdmVyc2lvbiBtZXRhZGF0YSBmb3IgJyR7dGFnfScuYCk7XG4gICAgICB9XG4gICAgfVxuICB9XG5cbiAgcmV0dXJuIG1ldGFkYXRhO1xufVxuXG5leHBvcnQgYXN5bmMgZnVuY3Rpb24gZmV0Y2hQYWNrYWdlTWFuaWZlc3QoXG4gIG5hbWU6IHN0cmluZyxcbiAgbG9nZ2VyOiBsb2dnaW5nLkxvZ2dlckFwaSxcbiAgb3B0aW9ucz86IHtcbiAgICByZWdpc3RyeT86IHN0cmluZztcbiAgICB1c2luZ1lhcm4/OiBib29sZWFuO1xuICAgIHZlcmJvc2U/OiBib29sZWFuO1xuICB9LFxuKTogUHJvbWlzZTxQYWNrYWdlTWFuaWZlc3Q+IHtcbiAgY29uc3QgeyB1c2luZ1lhcm4sIHZlcmJvc2UsIHJlZ2lzdHJ5IH0gPSB7XG4gICAgcmVnaXN0cnk6IHVuZGVmaW5lZCxcbiAgICB1c2luZ1lhcm46IGZhbHNlLFxuICAgIHZlcmJvc2U6IGZhbHNlLFxuICAgIC4uLm9wdGlvbnMsXG4gIH07XG5cbiAgZW5zdXJlTnBtcmMobG9nZ2VyLCB1c2luZ1lhcm4sIHZlcmJvc2UpO1xuXG4gIGNvbnN0IHJlc3BvbnNlID0gYXdhaXQgcGFjb3RlLm1hbmlmZXN0KFxuICAgIG5hbWUsXG4gICAge1xuICAgICAgJ2Z1bGwtbWV0YWRhdGEnOiB0cnVlLFxuICAgICAgLi4ubnBtcmMsXG4gICAgICAuLi4ocmVnaXN0cnkgPyB7IHJlZ2lzdHJ5IH0gOiB7fSksXG4gICAgfSxcbiAgKTtcblxuICByZXR1cm4gbm9ybWFsaXplTWFuaWZlc3QocmVzcG9uc2UpO1xufVxuIl19