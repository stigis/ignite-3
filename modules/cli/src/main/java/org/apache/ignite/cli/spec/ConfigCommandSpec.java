/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.ignite.cli.spec;

import javax.inject.Inject;
import org.apache.ignite.cli.builtins.config.ConfigurationClient;
import picocli.CommandLine;

/**
 * Commands get/put Ignite node configurations.
 */
@CommandLine.Command(
        name = "config",
        description = "Inspects and updates Ignite cluster configuration.",
        subcommands = {
                ConfigCommandSpec.GetConfigCommandSpec.class,
                ConfigCommandSpec.SetConfigCommandSpec.class
        }
)
public class ConfigCommandSpec extends CategorySpec {
    /**
     * Command for get Ignite node configurations.
     */
    @CommandLine.Command(name = "get", description = "Gets current Ignite cluster configuration values.")
    public static class GetConfigCommandSpec extends CommandSpec {
        /** Configuration client for REST node API. */
        @Inject
        private ConfigurationClient configurationClient;

        /** Command option for setting custom node host. */
        @CommandLine.Mixin
        private NodeEndpointOptions cfgHostnameOptions;

        /** Command option for setting HOCON based config selector. */
        @CommandLine.Option(
                names = "--selector",
                description = "Configuration selector (example: local.baseline)"
        )
        private String selector;

        /** Configuration type: {@code node} or {@code cluster}. */
        @CommandLine.Option(
                names = "--type",
                description = "Configuration type (\"node\" or \"cluster\")",
                required = true
        )
        //TODO: Fix in https://issues.apache.org/jira/browse/IGNITE-15306
        private String type;

        /** {@inheritDoc} */
        @Override
        public void run() {
            spec.commandLine().getOut().println(
                    configurationClient.get(cfgHostnameOptions.host(), cfgHostnameOptions.port(), selector, type)
            );
        }
    }

    /**
     * Command for setting Ignite node configuration.
     */
    @CommandLine.Command(
            name = "set",
            description = "Updates Ignite cluster configuration values."
    )
    public static class SetConfigCommandSpec extends CommandSpec {
        /** Configuration client for REST node APi. */
        @Inject
        private ConfigurationClient configurationClient;

        /** Config string with valid HOCON value. */
        @CommandLine.Parameters(paramLabel = "hocon", description = "Configuration in Hocon format")
        private String cfg;

        /** Command option for setting custom node host. */
        @CommandLine.Mixin
        private NodeEndpointOptions cfgHostnameOptions;

        /** Configuration type: {@code node} or {@code cluster}. */
        //TODO: Fix in https://issues.apache.org/jira/browse/IGNITE-15306
        @CommandLine.Option(
                names = "--type",
                description = "Configuration type (\"node\" or \"cluster\")",
                required = true
        )
        private String type;

        /** {@inheritDoc} */
        @Override
        public void run() {
            configurationClient.set(
                    cfgHostnameOptions.host(),
                    cfgHostnameOptions.port(),
                    cfg,
                    spec.commandLine().getOut(),
                    spec.commandLine().getColorScheme(),
                    type
            );
        }
    }

}
