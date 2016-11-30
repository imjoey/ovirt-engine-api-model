/*
Copyright (c) 2015-2016 Red Hat, Inc.

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

  http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
*/

package types;

import org.ovirt.api.metamodel.annotations.Link;
import org.ovirt.api.metamodel.annotations.Type;

@Type
public interface Host extends Identified {
    String address();
    HostStatus status();
    String statusDetail();
    Certificate certificate();
    ExternalStatus externalStatus();
    Integer port();

    /**
     * Indicates if the host contains a full installation of the operating system or a scaled-down version intended
     * only to host virtual machines.
     *
     * @author Tahlia Richardson <trichard@redhat.com>
     * @date 31 Oct 2016
     * @status updated_by_docs
     */
    HostType type();

    Spm spm();

    /**
     * The version of VDSM.
     *
     * For example:
     *
     * [source]
     * ----
     * GET /ovirt-engine/api/hosts/123
     * ----
     *
     * This `GET` request will return the following output:
     *
     * [source,xml]
     * ----
     * <host>
     *   ...
     *   <version>
     *     <build>999</build>
     *     <full_version>vdsm-4.18.999-419.gitcf06367.el7</full_version>
     *     <major>4</major>
     *     <minor>18</minor>
     *     <revision>0</revision>
     *   </version>
     *   ...
     * </host>
     * ----
     *
     * @author Tomas Jelinek <tjelinek@redhat.com>
     * @author Tahlia Richardson <trichard@redhat.com>
     * @date 31 Oct 2016
     * @status updated_by_docs
     */
    Version version();
    HardwareInformation hardwareInformation();
    PowerManagement powerManagement();

    /**
     * Kernel SamePage Merging (KSM) reduces references to memory pages from multiple identical pages to a single page
     * reference. This helps with optimization for memory density.
     *
     * For example, to enable KSM for host `123`, send a request like this:
     *
     * ....
     * PUT /ovirt-engine/api/hosts/123
     * ....
     *
     * With a request body like this:
     *
     * [source,xml]
     * ----
     * <host>
     *   <ksm>
     *     <enabled>true</enabled>
     *   </ksm>
     * </host>
     * ----
     *
     * @author Tomas Jelinek <tjelinek@redhat.com>
     * @date 14 Sept 2016
     * @status added
     */
    Ksm ksm();

    /**
     * Transparent huge page support expands the size of memory pages beyond the standard 4 KiB limit. This reduces
     * memory consumption and increases host performance.
     *
     * For example, to enable transparent huge page support for host `123`, send a request like this:
     *
     * ....
     * PUT /ovirt-engine/api/hosts/123
     * ....
     *
     * With a request body like this:
     *
     * [source,xml]
     * ----
     * <host>
     *   <transparent_hugepages>
     *     <enabled>true</enabled>
     *   </transparent_hugepages>
     * </host>
     * ----
     *
     * @author Tomas Jelinek <tjelinek@redhat.com>
     * @date 14 Sept 2016
     * @status added
     */
    TransparentHugePages transparentHugePages();

    IscsiDetails iscsi();

    /**
     * When creating a new host, a root password is required if the password authentication method is chosen,
     * but this is not subsequently included in the representation.
     *
     * @author Tahlia Richardson <trichard@redhat.com>
     * @date 31 Oct 2016
     * @status updated_by_docs
     */
    String rootPassword();

    Ssh ssh();
    Cpu cpu();
    Integer memory();
    Integer maxSchedulingMemory();
    VmSummary summary();
    Boolean overrideIptables();

    /**
     * The protocol that the engine uses to communicate with
     * the host.
     *
     * WARNING: Since version 4.1 of the engine the protocol
     * is always set to `stomp` since `xml` was removed.
     *
     * @author Piotr Kliczewski <pkliczew@redhat.com>
     * @date 30 Nov 2016
     * @status added
     */
    HostProtocol protocol();
    OperatingSystem os();
    Version libvirtVersion();

    /**
     * Optionally specify the display address of this host explicitly.
     *
     * @author Tahlia Richardson <trichard@redhat.com>
     * @date 31 Oct 2016
     * @status updated_by_docs
     */
    Display display();

    HostedEngine hostedEngine();
    KdumpStatus kdumpStatus();
    SeLinux seLinux();
    AutoNumaStatus autoNumaStatus();
    Boolean numaSupported();
    Boolean updateAvailable();
    HostDevicePassthrough devicePassthrough();

    @Link Cluster cluster();
    @Link Hook[] hooks();
    @Link ExternalHostProvider externalHostProvider();
    @Link StorageConnectionExtension[] storageConnectionExtensions();
    @Link AffinityLabel[] affinityLabels();
    @Link Device[] devices();
    @Link Agent[] agents();
    @Link KatelloErratum[] katelloErrata();
    @Link NetworkAttachment[] networkAttachments();
    @Link Nic[] nics();
    @Link NumaNode[] numaNodes();
    @Link Permission[] permissions();
    @Link Statistic[] statistics();
    @Link HostStorage[] storages();
    @Link Tag[] tags();
    @Link UnmanagedNetwork[] unmanagedNetworks();
}
