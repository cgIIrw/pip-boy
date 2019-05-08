package gc

import rtda.heap.Instance_
import rtda.stack.Slot_

/**
 * @Author: cgrw
 * @Url: https://github.com/cgIIrw/pip-boy
 */

var arr = arrayOfNulls<Slot_>(100)

class Oop_ {
    var i: Int? = null
    var size: Int? = null
    var mark: Boolean = false
}

var oopMap = HashMap<Instance_, Oop_>()

// 垃圾回收方法
fun gC() {
    mark_phase()
    sweep_phase()
}

fun mark_phase() {

}

fun sweep_phase() {

}

fun mark(slot: Slot_) {
    if (slot.ref == null)
        return

    var oop = oopMap[slot.ref]
    if (!oop!!.mark) {
        oop.mark = true

        for (i in oop.i!! until (oop.i!! + oop.size!!)) {
            if (arr[i]!!.ref != null) {
                var oop0 = oopMap[arr[i]!!.ref]
                if (!oop0!!.mark) {
                    oop0.mark = true
                }
            }
        }
    }
}

