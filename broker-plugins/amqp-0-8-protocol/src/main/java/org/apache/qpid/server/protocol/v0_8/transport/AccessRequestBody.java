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
import org.apache.qpid.server.protocol.v0_8.AMQShortString;

public class AccessRequestBody extends AMQMethodBodyImpl implements EncodableAMQDataBlock, AMQMethodBody
{

    public static final int CLASS_ID =  30;
    public static final int METHOD_ID = 10;

    // Fields declared in specification
    private final AMQShortString _realm; // [realm]
    private final byte _bitfield0; // [exclusive, passive, active, write, read]

    public AccessRequestBody(
            AMQShortString realm,
            boolean exclusive,
            boolean passive,
            boolean active,
            boolean write,
            boolean read
                            )
    {
        _realm = realm;
        byte bitfield0 = (byte)0;
        if( exclusive )
        {
            bitfield0 = (byte) (((int) bitfield0) | (1 << 0));
        }

        if( passive )
        {
            bitfield0 = (byte) (((int) bitfield0) | (1 << 1));
        }

        if( active )
        {
            bitfield0 = (byte) (((int) bitfield0) | (1 << 2));
        }

        if( write )
        {
            bitfield0 = (byte) (((int) bitfield0) | (1 << 3));
        }

        if( read )
        {
            bitfield0 = (byte) (((int) bitfield0) | (1 << 4));
        }
        _bitfield0 = bitfield0;
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

    public final AMQShortString getRealm()
    {
        return _realm;
    }
    public final boolean getExclusive()
    {
        return (((int)(_bitfield0)) & ( 1 << 0)) != 0;
    }
    public final boolean getPassive()
    {
        return (((int)(_bitfield0)) & ( 1 << 1)) != 0;
    }
    public final boolean getActive()
    {
        return (((int)(_bitfield0)) & ( 1 << 2)) != 0;
    }
    public final boolean getWrite()
    {
        return (((int)(_bitfield0)) & ( 1 << 3)) != 0;
    }
    public final boolean getRead()
    {
        return (((int)(_bitfield0)) & ( 1 << 4)) != 0;
    }

    @Override
    protected int getBodySize()
    {
        int size = 1;
        size += getSizeOf( _realm );
        return size;
    }

    @Override
    public void writeMethodPayload(QpidByteBuffer buffer)
    {
        writeAMQShortString( buffer, _realm );
        writeBitfield( buffer, _bitfield0 );
    }

    @Override
    public boolean execute(MethodDispatcher dispatcher, int channelId) throws QpidException
	{
        return dispatcher.dispatchAccessRequest(this, channelId);
	}

    @Override
    public String toString()
    {
        StringBuilder buf = new StringBuilder("[AccessRequestBodyImpl: ");
        buf.append( "realm=" );
        buf.append(  getRealm() );
        buf.append( ", " );
        buf.append( "exclusive=" );
        buf.append(  getExclusive() );
        buf.append( ", " );
        buf.append( "passive=" );
        buf.append(  getPassive() );
        buf.append( ", " );
        buf.append( "active=" );
        buf.append(  getActive() );
        buf.append( ", " );
        buf.append( "write=" );
        buf.append(  getWrite() );
        buf.append( ", " );
        buf.append( "read=" );
        buf.append(  getRead() );
        buf.append("]");
        return buf.toString();
    }

    public static void process(final QpidByteBuffer buffer,
                               final ServerChannelMethodProcessor dispatcher)
    {
        AMQShortString realm = AMQShortString.readAMQShortString(buffer);
        byte bitfield = buffer.get();
        boolean exclusive = (bitfield & 0x01) == 0x1 ;
        boolean passive = (bitfield & 0x02) == 0x2 ;
        boolean active = (bitfield & 0x04) == 0x4 ;
        boolean write = (bitfield & 0x08) == 0x8 ;
        boolean read = (bitfield & 0x10) == 0x10 ;
        if(!dispatcher.ignoreAllButCloseOk())
        {
            dispatcher.receiveAccessRequest(realm, exclusive, passive, active, write, read);
        }
    }
}
