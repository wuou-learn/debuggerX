package io.debuggerx.protocol.jdwp;


import java.nio.ByteBuffer;

/**
 * @author ouwu
 */
public class Location {
    private final byte typeTag;
    private final ClassId classId;
    private final MethodId methodId;
    private final long index;

    public static Location read(ByteBuffer byteBuffer, IdSizes idSizes) {
        return new Location(byteBuffer, idSizes);
    }

    Location(ByteBuffer byteBuffer, IdSizes idSizes) {
        typeTag = byteBuffer.get();
        classId = ClassId.read(byteBuffer, idSizes);
        methodId = MethodId.read(byteBuffer, idSizes);
        index = byteBuffer.getLong();
    }

    public Location(byte typeTag, ClassId classId, MethodId methodId, long index) {
        this.typeTag = typeTag;
        this.classId = classId;
        this.methodId = methodId;
        this.index = index;
    }

    public byte getTypeTag() {
        return typeTag;
    }

    public ClassId getClassId() {
        return classId;
    }

    public MethodId getMethodId() {
        return methodId;
    }

    public long getIndex() {
        return index;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Location location = (Location) o;

        if (typeTag != location.typeTag) {
            return false;
        }
        if (index != location.index) {
            return false;
        }
        if (classId != null ? !classId.equals(location.classId) : location.classId != null) {
            return false;
        }
        return methodId != null ? methodId.equals(location.methodId) : location.methodId == null;
    }

    @Override
    public int hashCode() {
        int result = (int) typeTag;
        result = 31 * result + (classId != null ? classId.hashCode() : 0);
        result = 31 * result + (methodId != null ? methodId.hashCode() : 0);
        result = 31 * result + (int) (index ^ (index >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "Location{" +
                "typeTag=" + typeTag +
                ", classId=" + classId +
                ", methodId=" + methodId +
                ", index=" + index +
                '}';
    }
}
