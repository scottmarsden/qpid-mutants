/*
 *
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 *
 */

/*
 * This file is auto-generated by Qpid Gentools v.0.1 - do not modify.
 * Supported AMQP version:
 *   8-0
 */

package org.apache.qpid.server.protocol.v0_8.transport;

import org.apache.qpid.server.QpidException;
import org.apache.qpid.server.bytebuffer.QpidByteBuffer;

public class ConfirmSelectBody extends AMQMethodBodyImpl implements EncodableAMQDataBlock, AMQMethodBody
{

    public static final int CLASS_ID =  85;
    public static final int METHOD_ID = 10;

    // Fields declared in specification
    private final boolean _nowait; // [active]

    public ConfirmSelectBody(boolean nowait)
    {
        _nowait = nowait;
    }

    @Override
    public int getClazz()
    {
        return CLASS_ID;
    }

    @Override
    public int getMethod()
    {
        return METHOD_ID;
    }

    public final boolean getNowait()
    {
        return _nowait;
    }

    @Override
    protected int getBodySize()
    {
        return 1;
    }

    @Override
    public void writeMethodPayload(QpidByteBuffer buffer)
    {
        writeBitfield( buffer, _nowait ? (byte)1 : (byte)0 );
    }

    @Override
    public boolean execute(MethodDispatcher dispatcher, int channelId) throws QpidException
	{
        return dispatcher.dispatchConfirmSelect(this, channelId);
	}

    @Override
    public String toString()
    {
        StringBuilder buf = new StringBuilder("[ConfirmSelectBody: ");
        buf.append( "active=" );
        buf.append(  getNowait() );
        buf.append("]");
        return buf.toString();
    }

    public static void process(final QpidByteBuffer buffer,
                               final ServerChannelMethodProcessor dispatcher)
    {
        boolean nowait = (buffer.get() & 0x01) == 0x01;
        if(!dispatcher.ignoreAllButCloseOk())
        {
            dispatcher.receiveConfirmSelect(nowait);
        }
    }
}
