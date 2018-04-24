package com.qdesrame.openapi.diff.model.schema;

import com.qdesrame.openapi.diff.model.Changed;
import com.qdesrame.openapi.diff.model.DiffContext;
import com.qdesrame.openapi.diff.model.DiffResult;

import java.util.Objects;
import java.util.Optional;

public class ChangedWriteOnly implements Changed {
    private final DiffContext context;
    private final boolean oldValue;
    private final boolean newValue;
//    private final boolean required;

    public ChangedWriteOnly(DiffContext context, Boolean oldValue, Boolean newValue) {
        this.context = context;
        this.oldValue = Optional.ofNullable(oldValue).orElse(false);
        this.newValue = Optional.ofNullable(newValue).orElse(false);
//        this.required = required;
    }

    @Override
    public DiffResult isChanged() {
        if (Objects.equals(oldValue, newValue)) {
            return DiffResult.NO_CHANGES;
        }
        if (context.isRequest()) {
            return DiffResult.COMPATIBLE;
        }
        if (context.isResponse()) {
            if (Boolean.TRUE.equals(newValue)) {
                return DiffResult.INCOMPATIBLE;
            } else {
                return DiffResult.COMPATIBLE;
            }
        }
        return DiffResult.UNKNOWN;
    }
}
