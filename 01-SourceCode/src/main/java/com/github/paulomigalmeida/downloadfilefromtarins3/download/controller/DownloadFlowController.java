/*
 * The MIT License
 *
 * Copyright (c) 2017-2017 Paulo Miguel Almeida <paulo.miguel.almeida.rodenas(at)gmail.com>
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package com.github.paulomigalmeida.downloadfilefromtarins3.download.controller;

import com.github.paulomigalmeida.downloadfilefromtarins3.base.controller.AbstractController;
import com.github.paulomigalmeida.downloadfilefromtarins3.base.service.s3.S3Service;
import com.github.paulomigalmeida.downloadfilefromtarins3.base.tar.TarFileEntry;
import com.github.paulomigalmeida.downloadfilefromtarins3.download.operation.TarMetadataFileSearcher;
import com.github.paulomigalmeida.downloadfilefromtarins3.download.parser.DownloadProgramArgumentsSingleton;

import static com.github.paulomigalmeida.downloadfilefromtarins3.base.utils.IConstants.TAR_METADATA_FILE_SUFFIX;

public class DownloadFlowController extends AbstractController<Void> {

    @Override
    public Void execute() throws Exception {
        S3Service s3Service = new S3Service();

        // Download tar metadata file
        byte[] tarMetadataFileBytes = s3Service.downloadFile(
                DownloadProgramArgumentsSingleton.getInstance().getBucketName(),
                DownloadProgramArgumentsSingleton.getInstance().getKeyName() + TAR_METADATA_FILE_SUFFIX
        );

        // search for tar file entry
        TarMetadataFileSearcher tarMetadataFileSearcher = new TarMetadataFileSearcher(
                tarMetadataFileBytes,
                DownloadProgramArgumentsSingleton.getInstance().getTarFileEntry()
        );
        TarFileEntry tarFileEntry = tarMetadataFileSearcher.performOperation();

        // if found download it to the specified destination
        s3Service.downloadFile(
                DownloadProgramArgumentsSingleton.getInstance().getBucketName(),
                DownloadProgramArgumentsSingleton.getInstance().getKeyName(),
                DownloadProgramArgumentsSingleton.getInstance().getOutput(),
                tarFileEntry.getFileStartPosition(),
                tarFileEntry.getFileEndPosition()
                );
        return null;
    }

}
